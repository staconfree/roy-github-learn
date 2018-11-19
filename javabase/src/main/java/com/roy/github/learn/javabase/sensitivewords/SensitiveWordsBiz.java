package com.roy.github.learn.javabase.sensitivewords;

import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 敏感词过滤
 * Created by roy on 2018/11/19.
 * https://my.oschina.net/yanyimin/blog/1593974
 */
public class SensitiveWordsBiz {
    private static Logger logger = LoggerFactory.getLogger(SensitiveWordsBiz.class);

    private static Map<String,Object> SensitiveWordsCache = new HashMap<>();

    static {
        logger.info("开始构建敏感词库");
        getSensitiveWordMap();
        logger.info("敏感词库构建完毕");

    }

    public static void main(String[] args) {
        SensitiveWordsBiz sensitiveWordsBiz = new SensitiveWordsBiz();
        String word = " fuck you，练法轮功去吧。。";
        List<String> sensitiveWordIndexs = sensitiveWordsBiz.listSensitiveWordIndexs(word);
        List<Integer> replaceIndex = new ArrayList<>();
        if (sensitiveWordIndexs!=null){
            for (String index : sensitiveWordIndexs) {
                for (int i = Integer.valueOf(index.split("-")[0]);i<=Integer.valueOf(index.split("-")[1]);i++) {
                    replaceIndex.add(i);
                }
            }
        }
        System.out.println(replaceSensitive(word, replaceIndex));
    }

    private static String replaceSensitive(String word, List<Integer> replaceIndex) {
        if (replaceIndex==null||replaceIndex.isEmpty()){
            return word;
        }
        char[] cs = word.toCharArray();
        for (int i=0;i<cs.length;i++){
            if (replaceIndex.contains(i)){
                cs[i]='*';
            }
        }
        return new String(cs);
    }

    /**
     * 构造trie树 - 构建敏感词库
     * @param set
     */
    @SuppressWarnings("unchecked")
    public static Map<String,Object> buildSensitiveWordMap(Set<String> set, Map<String,Object> sensitiveWordMap){
        Map<String,Object> childMap = new HashMap<>(); // 子Map
        Map<String,Object> parentMap = new HashMap<>(); // 父map

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            char[] word = iterator.next().toCharArray();
            parentMap = sensitiveWordMap;
            for (int i=0;i<word.length;i++){
                childMap = (Map<String, Object>) parentMap.get(String.valueOf(word[i]));
                if(childMap==null){
                    childMap = new HashMap<>();
                    parentMap.put(String.valueOf(word[i]),childMap); // 如果该字符不存在，则插入
                }

                parentMap = childMap; // 将子map的引用赋值给父map，进行下一轮循环
                if(i==word.length-1){
                    parentMap.put("isEnd",true);
                }else{
                    parentMap.put("isEnd",false);
                }
            }
        }

        return sensitiveWordMap;
    }

    /**
     * 从敏感词库中去除敏感词
     * @param set
     */
    @SuppressWarnings("unchecked")
    public void removeSensitiveWordFromMap(Set<String> set){
        Map<String,Object> sensitiveWordMap = getSensitiveWordMap();
        Map<String,Object> childMap = new HashMap<>(); //子Map
        Map<String,Object> parentMap = new HashMap<>(); //父map

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            char[] word = iterator.next().toCharArray();
            parentMap = sensitiveWordMap;
            for (int i=0;i<word.length;i++){
                childMap = (Map<String, Object>) parentMap.get(String.valueOf(word[i]));
                if(childMap==null){
                    childMap = new HashMap<>();
                    parentMap.put(String.valueOf(word[i]),childMap); // 如果该字符不存在，则插入
                }

                parentMap = childMap; // 将子map的引用赋值给父map，进行下一轮循环
                parentMap.put("isEnd",false); //始终设置为false，则将此敏感词变相清除了
            }
        }

        SensitiveWordsCache.put("sensitiveWords",sensitiveWordMap);
    }

    /**
     * 在敏感词库中添加敏感词
     * @param set
     */
    public void addSensitiveWordFromMap(Set<String> set){
        Map<String,Object> sensitiveWordMap = buildSensitiveWordMap(set, getSensitiveWordMap());
        SensitiveWordsCache.put("sensitiveWords",sensitiveWordMap);
    }

    /**
     * 重新构建
     * @return
     */
    public static Map<String,Object> reBuild(){
        Map<String,Object> sensitiveWordMap = new HashMap<>();
        HashSet<String> set = getSensitiveWords();
        sensitiveWordMap = buildSensitiveWordMap(set,new HashMap<String,Object>(set.size()));
        SensitiveWordsCache.put("sensitiveWords",sensitiveWordMap); // 敏感词数据一般不会变动，设置一个月的超时时间
        return sensitiveWordMap;
    }

    /**
     * 敏感词过滤
     * @param text
     * @return
     */
    public String doFilter(String text){
        char[] word = text.toCharArray();
        Map<String,Object> sensitiveWordMap = getSensitiveWordMap();
        for(int i=0;i<word.length-1;i++){
            int index = doFilter(word,sensitiveWordMap,i);
            if(index==0){
                continue;
            }else{
                String words = text.substring(i, index+1);
                text = text.replace(words,words.replaceAll("[\\s\\S]","*"));
                i = index;
            }
        }
        return text;
    }

    @SuppressWarnings("unchecked")
    public int doFilter(char[] word,Map<String,Object> currentMap,int i){
        if(i>=word.length){
            return 0;
        }
        String s = String.valueOf(word[i]);
        currentMap = (Map<String, Object>) currentMap.get(s.toLowerCase()); //英文都存小写的
        if(currentMap!=null){
            if((boolean)currentMap.get("isEnd")){
                return i;
            }else{
                return doFilter(word,currentMap,i+1);
            }
        }
        return 0;
    }

    /**
     * 是否包含敏感词
     * @param text
     * @return
     */
    public boolean isContian(String text){
        char[] word = text.toCharArray();
        Map<String,Object> sensitiveWordMap = getSensitiveWordMap();
        for(int i=0;i<word.length;i++){
            int index = doFilter(word,sensitiveWordMap,i);
            if(index==0){
                continue;
            }else{
                return true;
            }
        }
        return false;
    }

    /**
     * 查询包含的敏感词
     * @param text
     * @return 如：1-4 代表从第一位到第4位是敏感词
     */
    public List<String> listSensitiveWordIndexs(String text){
        char[] word = text.toCharArray();
        Map<String,Object> sensitiveWordMap = getSensitiveWordMap();
        List<String> list = new ArrayList<>();
        for(int i=0;i<word.length;i++){
            int index = doFilter(word,sensitiveWordMap,i);
            if(index==0){
                continue;
            }else{
                list.add(i+"-"+index);
            }
        }
        if (!list.isEmpty()){
            return list;
        }
        return null;
    }

    /**
     * 获取敏感词
     * @return
     */
    public static HashSet<String> getSensitiveWords(){
        // todo 从数据库获取
        List<String> list = new ArrayList<>();
        list.add("共产党");
        list.add("法轮功");
        list.add("fuck");
        list.add("bitch");
        HashSet<String> set= Sets.newHashSet(list);
        return set;
    }

    /**
     * 初始化
     */
    @SuppressWarnings("unchecked")
    public static Map<String,Object> getSensitiveWordMap(){
        Object trieTree = SensitiveWordsCache.get("sensitiveWords");
        Map<String,Object> sensitiveWordMap = new HashMap<>();
        if (trieTree!=null){
            sensitiveWordMap = (Map<String, Object>) trieTree;
        }else {
            sensitiveWordMap = reBuild();
        }

        return sensitiveWordMap;
    }

}
