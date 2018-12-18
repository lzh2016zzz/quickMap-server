package org.quickmap.dataService.dao.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
*
*  @author author
*/
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 1545134203291L;


    /**
    * 主键
    * 
    * isNullAble:0
    */
    private String id;

    /**
    * 
    * isNullAble:1
    */
    private String filename;

    /**
    * 
    * isNullAble:1
    */
    private Long size;

    /**
    * 
    * isNullAble:1
    */
    private String path;

    /**
    * 
    * isNullAble:1
    */
    private Long timestamp;

    /**
    * 
    * isNullAble:1
    */
    private Integer author;

    /**
    * 
    * isNullAble:0,defaultVal:1
    */
    private Integer isdel;

    /**
    * 
    * isNullAble:1
    */
    private String thumbImagePath;

    /**
    * 
    * isNullAble:1
    */
    private String suffix;


    public void setId(String id){this.id = id;}

    public String getId(){return this.id;}

    public void setFilename(String filename){this.filename = filename;}

    public String getFilename(){return this.filename;}

    public void setSize(Long size){this.size = size;}

    public Long getSize(){return this.size;}

    public void setPath(String path){this.path = path;}

    public String getPath(){return this.path;}

    public void setTimestamp(Long timestamp){this.timestamp = timestamp;}

    public Long getTimestamp(){return this.timestamp;}

    public void setAuthor(Integer author){this.author = author;}

    public Integer getAuthor(){return this.author;}

    public void setIsdel(Integer isdel){this.isdel = isdel;}

    public Integer getIsdel(){return this.isdel;}

    public void setThumbImagePath(String thumbImagePath){this.thumbImagePath = thumbImagePath;}

    public String getThumbImagePath(){return this.thumbImagePath;}

    public void setSuffix(String suffix){this.suffix = suffix;}

    public String getSuffix(){return this.suffix;}
    @Override
    public String toString() {
        return "FileInfo{" +
                "id='" + id + '\'' +
                "filename='" + filename + '\'' +
                "size='" + size + '\'' +
                "path='" + path + '\'' +
                "timestamp='" + timestamp + '\'' +
                "author='" + author + '\'' +
                "isdel='" + isdel + '\'' +
                "thumbImagePath='" + thumbImagePath + '\'' +
                "suffix='" + suffix + '\'' +
            '}';
    }

    public static Builder Build(){return new Builder();}

    public static ConditionBuilder ConditionBuild(){return new ConditionBuilder();}

    public static UpdateBuilder UpdateBuild(){return new UpdateBuilder();}

    public static QueryBuilder QueryBuild(){return new QueryBuilder();}

    public static class UpdateBuilder {

        private FileInfo set;

        private ConditionBuilder where;

        public UpdateBuilder set(FileInfo set){
            this.set = set;
            return this;
        }

        public FileInfo getSet(){
            return this.set;
        }

        public UpdateBuilder where(ConditionBuilder where){
            this.where = where;
            return this;
        }

        public ConditionBuilder getWhere(){
            return this.where;
        }

        public UpdateBuilder build(){
            return this;
        }
    }

    public static class QueryBuilder extends FileInfo{
        /**
        * 需要返回的列
        */
        private Map<String,Object> fetchFields;

        public Map<String,Object> getFetchFields(){return this.fetchFields;}

        private List<String> idList;

        public List<String> getIdList(){return this.idList;}


        private List<String> fuzzyId;

        public List<String> getFuzzyId(){return this.fuzzyId;}

        private List<String> rightFuzzyId;

        public List<String> getRightFuzzyId(){return this.rightFuzzyId;}
        private List<String> filenameList;

        public List<String> getFilenameList(){return this.filenameList;}


        private List<String> fuzzyFilename;

        public List<String> getFuzzyFilename(){return this.fuzzyFilename;}

        private List<String> rightFuzzyFilename;

        public List<String> getRightFuzzyFilename(){return this.rightFuzzyFilename;}
        private List<Long> sizeList;

        public List<Long> getSizeList(){return this.sizeList;}

        private Long sizeSt;

        private Long sizeEd;

        public Long getSizeSt(){return this.sizeSt;}

        public Long getSizeEd(){return this.sizeEd;}

        private List<String> pathList;

        public List<String> getPathList(){return this.pathList;}


        private List<String> fuzzyPath;

        public List<String> getFuzzyPath(){return this.fuzzyPath;}

        private List<String> rightFuzzyPath;

        public List<String> getRightFuzzyPath(){return this.rightFuzzyPath;}
        private List<Long> timestampList;

        public List<Long> getTimestampList(){return this.timestampList;}

        private Long timestampSt;

        private Long timestampEd;

        public Long getTimestampSt(){return this.timestampSt;}

        public Long getTimestampEd(){return this.timestampEd;}

        private List<Integer> authorList;

        public List<Integer> getAuthorList(){return this.authorList;}

        private Integer authorSt;

        private Integer authorEd;

        public Integer getAuthorSt(){return this.authorSt;}

        public Integer getAuthorEd(){return this.authorEd;}

        private List<Integer> isdelList;

        public List<Integer> getIsdelList(){return this.isdelList;}

        private Integer isdelSt;

        private Integer isdelEd;

        public Integer getIsdelSt(){return this.isdelSt;}

        public Integer getIsdelEd(){return this.isdelEd;}

        private List<String> thumbImagePathList;

        public List<String> getThumbImagePathList(){return this.thumbImagePathList;}


        private List<String> fuzzyThumbImagePath;

        public List<String> getFuzzyThumbImagePath(){return this.fuzzyThumbImagePath;}

        private List<String> rightFuzzyThumbImagePath;

        public List<String> getRightFuzzyThumbImagePath(){return this.rightFuzzyThumbImagePath;}
        private List<String> suffixList;

        public List<String> getSuffixList(){return this.suffixList;}


        private List<String> fuzzySuffix;

        public List<String> getFuzzySuffix(){return this.fuzzySuffix;}

        private List<String> rightFuzzySuffix;

        public List<String> getRightFuzzySuffix(){return this.rightFuzzySuffix;}
        private QueryBuilder (){
            this.fetchFields = new HashMap<>();
        }

        public QueryBuilder fuzzyId (List<String> fuzzyId){
            this.fuzzyId = fuzzyId;
            return this;
        }

        public QueryBuilder fuzzyId (String ... fuzzyId){
            this.fuzzyId = solveNullList(fuzzyId);
            return this;
        }

        public QueryBuilder rightFuzzyId (List<String> rightFuzzyId){
            this.rightFuzzyId = rightFuzzyId;
            return this;
        }

        public QueryBuilder rightFuzzyId (String ... rightFuzzyId){
            this.rightFuzzyId = solveNullList(rightFuzzyId);
            return this;
        }

        public QueryBuilder id(String id){
            setId(id);
            return this;
        }

        public QueryBuilder idList(String ... id){
            this.idList = solveNullList(id);
            return this;
        }

        public QueryBuilder idList(List<String> id){
            this.idList = id;
            return this;
        }

        public QueryBuilder fetchId(){
            setFetchFields("fetchFields","id");
            return this;
        }

        public QueryBuilder excludeId(){
            setFetchFields("excludeFields","id");
            return this;
        }

        public QueryBuilder fuzzyFilename (List<String> fuzzyFilename){
            this.fuzzyFilename = fuzzyFilename;
            return this;
        }

        public QueryBuilder fuzzyFilename (String ... fuzzyFilename){
            this.fuzzyFilename = solveNullList(fuzzyFilename);
            return this;
        }

        public QueryBuilder rightFuzzyFilename (List<String> rightFuzzyFilename){
            this.rightFuzzyFilename = rightFuzzyFilename;
            return this;
        }

        public QueryBuilder rightFuzzyFilename (String ... rightFuzzyFilename){
            this.rightFuzzyFilename = solveNullList(rightFuzzyFilename);
            return this;
        }

        public QueryBuilder filename(String filename){
            setFilename(filename);
            return this;
        }

        public QueryBuilder filenameList(String ... filename){
            this.filenameList = solveNullList(filename);
            return this;
        }

        public QueryBuilder filenameList(List<String> filename){
            this.filenameList = filename;
            return this;
        }

        public QueryBuilder fetchFilename(){
            setFetchFields("fetchFields","filename");
            return this;
        }

        public QueryBuilder excludeFilename(){
            setFetchFields("excludeFields","filename");
            return this;
        }

        public QueryBuilder sizeBetWeen(Long sizeSt,Long sizeEd){
            this.sizeSt = sizeSt;
            this.sizeEd = sizeEd;
            return this;
        }

        public QueryBuilder sizeGreaterEqThan(Long sizeSt){
            this.sizeSt = sizeSt;
            return this;
        }
        public QueryBuilder sizeLessEqThan(Long sizeEd){
            this.sizeEd = sizeEd;
            return this;
        }


        public QueryBuilder size(Long size){
            setSize(size);
            return this;
        }

        public QueryBuilder sizeList(Long ... size){
            this.sizeList = solveNullList(size);
            return this;
        }

        public QueryBuilder sizeList(List<Long> size){
            this.sizeList = size;
            return this;
        }

        public QueryBuilder fetchSize(){
            setFetchFields("fetchFields","size");
            return this;
        }

        public QueryBuilder excludeSize(){
            setFetchFields("excludeFields","size");
            return this;
        }

        public QueryBuilder fuzzyPath (List<String> fuzzyPath){
            this.fuzzyPath = fuzzyPath;
            return this;
        }

        public QueryBuilder fuzzyPath (String ... fuzzyPath){
            this.fuzzyPath = solveNullList(fuzzyPath);
            return this;
        }

        public QueryBuilder rightFuzzyPath (List<String> rightFuzzyPath){
            this.rightFuzzyPath = rightFuzzyPath;
            return this;
        }

        public QueryBuilder rightFuzzyPath (String ... rightFuzzyPath){
            this.rightFuzzyPath = solveNullList(rightFuzzyPath);
            return this;
        }

        public QueryBuilder path(String path){
            setPath(path);
            return this;
        }

        public QueryBuilder pathList(String ... path){
            this.pathList = solveNullList(path);
            return this;
        }

        public QueryBuilder pathList(List<String> path){
            this.pathList = path;
            return this;
        }

        public QueryBuilder fetchPath(){
            setFetchFields("fetchFields","path");
            return this;
        }

        public QueryBuilder excludePath(){
            setFetchFields("excludeFields","path");
            return this;
        }

        public QueryBuilder timestampBetWeen(Long timestampSt,Long timestampEd){
            this.timestampSt = timestampSt;
            this.timestampEd = timestampEd;
            return this;
        }

        public QueryBuilder timestampGreaterEqThan(Long timestampSt){
            this.timestampSt = timestampSt;
            return this;
        }
        public QueryBuilder timestampLessEqThan(Long timestampEd){
            this.timestampEd = timestampEd;
            return this;
        }


        public QueryBuilder timestamp(Long timestamp){
            setTimestamp(timestamp);
            return this;
        }

        public QueryBuilder timestampList(Long ... timestamp){
            this.timestampList = solveNullList(timestamp);
            return this;
        }

        public QueryBuilder timestampList(List<Long> timestamp){
            this.timestampList = timestamp;
            return this;
        }

        public QueryBuilder fetchTimestamp(){
            setFetchFields("fetchFields","timestamp");
            return this;
        }

        public QueryBuilder excludeTimestamp(){
            setFetchFields("excludeFields","timestamp");
            return this;
        }

        public QueryBuilder authorBetWeen(Integer authorSt,Integer authorEd){
            this.authorSt = authorSt;
            this.authorEd = authorEd;
            return this;
        }

        public QueryBuilder authorGreaterEqThan(Integer authorSt){
            this.authorSt = authorSt;
            return this;
        }
        public QueryBuilder authorLessEqThan(Integer authorEd){
            this.authorEd = authorEd;
            return this;
        }


        public QueryBuilder author(Integer author){
            setAuthor(author);
            return this;
        }

        public QueryBuilder authorList(Integer ... author){
            this.authorList = solveNullList(author);
            return this;
        }

        public QueryBuilder authorList(List<Integer> author){
            this.authorList = author;
            return this;
        }

        public QueryBuilder fetchAuthor(){
            setFetchFields("fetchFields","author");
            return this;
        }

        public QueryBuilder excludeAuthor(){
            setFetchFields("excludeFields","author");
            return this;
        }

        public QueryBuilder isdelBetWeen(Integer isdelSt,Integer isdelEd){
            this.isdelSt = isdelSt;
            this.isdelEd = isdelEd;
            return this;
        }

        public QueryBuilder isdelGreaterEqThan(Integer isdelSt){
            this.isdelSt = isdelSt;
            return this;
        }
        public QueryBuilder isdelLessEqThan(Integer isdelEd){
            this.isdelEd = isdelEd;
            return this;
        }


        public QueryBuilder isdel(Integer isdel){
            setIsdel(isdel);
            return this;
        }

        public QueryBuilder isdelList(Integer ... isdel){
            this.isdelList = solveNullList(isdel);
            return this;
        }

        public QueryBuilder isdelList(List<Integer> isdel){
            this.isdelList = isdel;
            return this;
        }

        public QueryBuilder fetchIsdel(){
            setFetchFields("fetchFields","isdel");
            return this;
        }

        public QueryBuilder excludeIsdel(){
            setFetchFields("excludeFields","isdel");
            return this;
        }

        public QueryBuilder fuzzyThumbImagePath (List<String> fuzzyThumbImagePath){
            this.fuzzyThumbImagePath = fuzzyThumbImagePath;
            return this;
        }

        public QueryBuilder fuzzyThumbImagePath (String ... fuzzyThumbImagePath){
            this.fuzzyThumbImagePath = solveNullList(fuzzyThumbImagePath);
            return this;
        }

        public QueryBuilder rightFuzzyThumbImagePath (List<String> rightFuzzyThumbImagePath){
            this.rightFuzzyThumbImagePath = rightFuzzyThumbImagePath;
            return this;
        }

        public QueryBuilder rightFuzzyThumbImagePath (String ... rightFuzzyThumbImagePath){
            this.rightFuzzyThumbImagePath = solveNullList(rightFuzzyThumbImagePath);
            return this;
        }

        public QueryBuilder thumbImagePath(String thumbImagePath){
            setThumbImagePath(thumbImagePath);
            return this;
        }

        public QueryBuilder thumbImagePathList(String ... thumbImagePath){
            this.thumbImagePathList = solveNullList(thumbImagePath);
            return this;
        }

        public QueryBuilder thumbImagePathList(List<String> thumbImagePath){
            this.thumbImagePathList = thumbImagePath;
            return this;
        }

        public QueryBuilder fetchThumbImagePath(){
            setFetchFields("fetchFields","thumbImagePath");
            return this;
        }

        public QueryBuilder excludeThumbImagePath(){
            setFetchFields("excludeFields","thumbImagePath");
            return this;
        }

        public QueryBuilder fuzzySuffix (List<String> fuzzySuffix){
            this.fuzzySuffix = fuzzySuffix;
            return this;
        }

        public QueryBuilder fuzzySuffix (String ... fuzzySuffix){
            this.fuzzySuffix = solveNullList(fuzzySuffix);
            return this;
        }

        public QueryBuilder rightFuzzySuffix (List<String> rightFuzzySuffix){
            this.rightFuzzySuffix = rightFuzzySuffix;
            return this;
        }

        public QueryBuilder rightFuzzySuffix (String ... rightFuzzySuffix){
            this.rightFuzzySuffix = solveNullList(rightFuzzySuffix);
            return this;
        }

        public QueryBuilder suffix(String suffix){
            setSuffix(suffix);
            return this;
        }

        public QueryBuilder suffixList(String ... suffix){
            this.suffixList = solveNullList(suffix);
            return this;
        }

        public QueryBuilder suffixList(List<String> suffix){
            this.suffixList = suffix;
            return this;
        }

        public QueryBuilder fetchSuffix(){
            setFetchFields("fetchFields","suffix");
            return this;
        }

        public QueryBuilder excludeSuffix(){
            setFetchFields("excludeFields","suffix");
            return this;
        }
        private <T>List<T> solveNullList(T ... objs){
            if (objs != null){
            List<T> list = new ArrayList<>();
                for (T item : objs){
                    if (item != null){
                        list.add(item);
                    }
                }
                return list;
            }
            return null;
        }

        public QueryBuilder fetchAll(){
            this.fetchFields.put("AllFields",true);
            return this;
        }

        public QueryBuilder addField(String ... fields){
            List<String> list = new ArrayList<>();
            if (fields != null){
                for (String field : fields){
                    list.add(field);
                }
            }
            this.fetchFields.put("otherFields",list);
            return this;
        }
        @SuppressWarnings("unchecked")
        private void setFetchFields(String key,String val){
            Map<String,Boolean> fields= (Map<String, Boolean>) this.fetchFields.get(key);
            if (fields == null){
                fields = new HashMap<>();
            }
            fields.put(val,true);
            this.fetchFields.put(key,fields);
        }

        public FileInfo build(){return this;}
    }


    public static class ConditionBuilder{
        private List<String> idList;

        public List<String> getIdList(){return this.idList;}


        private List<String> fuzzyId;

        public List<String> getFuzzyId(){return this.fuzzyId;}

        private List<String> rightFuzzyId;

        public List<String> getRightFuzzyId(){return this.rightFuzzyId;}
        private List<String> filenameList;

        public List<String> getFilenameList(){return this.filenameList;}


        private List<String> fuzzyFilename;

        public List<String> getFuzzyFilename(){return this.fuzzyFilename;}

        private List<String> rightFuzzyFilename;

        public List<String> getRightFuzzyFilename(){return this.rightFuzzyFilename;}
        private List<Long> sizeList;

        public List<Long> getSizeList(){return this.sizeList;}

        private Long sizeSt;

        private Long sizeEd;

        public Long getSizeSt(){return this.sizeSt;}

        public Long getSizeEd(){return this.sizeEd;}

        private List<String> pathList;

        public List<String> getPathList(){return this.pathList;}


        private List<String> fuzzyPath;

        public List<String> getFuzzyPath(){return this.fuzzyPath;}

        private List<String> rightFuzzyPath;

        public List<String> getRightFuzzyPath(){return this.rightFuzzyPath;}
        private List<Long> timestampList;

        public List<Long> getTimestampList(){return this.timestampList;}

        private Long timestampSt;

        private Long timestampEd;

        public Long getTimestampSt(){return this.timestampSt;}

        public Long getTimestampEd(){return this.timestampEd;}

        private List<Integer> authorList;

        public List<Integer> getAuthorList(){return this.authorList;}

        private Integer authorSt;

        private Integer authorEd;

        public Integer getAuthorSt(){return this.authorSt;}

        public Integer getAuthorEd(){return this.authorEd;}

        private List<Integer> isdelList;

        public List<Integer> getIsdelList(){return this.isdelList;}

        private Integer isdelSt;

        private Integer isdelEd;

        public Integer getIsdelSt(){return this.isdelSt;}

        public Integer getIsdelEd(){return this.isdelEd;}

        private List<String> thumbImagePathList;

        public List<String> getThumbImagePathList(){return this.thumbImagePathList;}


        private List<String> fuzzyThumbImagePath;

        public List<String> getFuzzyThumbImagePath(){return this.fuzzyThumbImagePath;}

        private List<String> rightFuzzyThumbImagePath;

        public List<String> getRightFuzzyThumbImagePath(){return this.rightFuzzyThumbImagePath;}
        private List<String> suffixList;

        public List<String> getSuffixList(){return this.suffixList;}


        private List<String> fuzzySuffix;

        public List<String> getFuzzySuffix(){return this.fuzzySuffix;}

        private List<String> rightFuzzySuffix;

        public List<String> getRightFuzzySuffix(){return this.rightFuzzySuffix;}

        public ConditionBuilder fuzzyId (List<String> fuzzyId){
            this.fuzzyId = fuzzyId;
            return this;
        }

        public ConditionBuilder fuzzyId (String ... fuzzyId){
            this.fuzzyId = solveNullList(fuzzyId);
            return this;
        }

        public ConditionBuilder rightFuzzyId (List<String> rightFuzzyId){
            this.rightFuzzyId = rightFuzzyId;
            return this;
        }

        public ConditionBuilder rightFuzzyId (String ... rightFuzzyId){
            this.rightFuzzyId = solveNullList(rightFuzzyId);
            return this;
        }

        public ConditionBuilder idList(String ... id){
            this.idList = solveNullList(id);
            return this;
        }

        public ConditionBuilder idList(List<String> id){
            this.idList = id;
            return this;
        }

        public ConditionBuilder fuzzyFilename (List<String> fuzzyFilename){
            this.fuzzyFilename = fuzzyFilename;
            return this;
        }

        public ConditionBuilder fuzzyFilename (String ... fuzzyFilename){
            this.fuzzyFilename = solveNullList(fuzzyFilename);
            return this;
        }

        public ConditionBuilder rightFuzzyFilename (List<String> rightFuzzyFilename){
            this.rightFuzzyFilename = rightFuzzyFilename;
            return this;
        }

        public ConditionBuilder rightFuzzyFilename (String ... rightFuzzyFilename){
            this.rightFuzzyFilename = solveNullList(rightFuzzyFilename);
            return this;
        }

        public ConditionBuilder filenameList(String ... filename){
            this.filenameList = solveNullList(filename);
            return this;
        }

        public ConditionBuilder filenameList(List<String> filename){
            this.filenameList = filename;
            return this;
        }

        public ConditionBuilder sizeBetWeen(Long sizeSt,Long sizeEd){
            this.sizeSt = sizeSt;
            this.sizeEd = sizeEd;
            return this;
        }

        public ConditionBuilder sizeGreaterEqThan(Long sizeSt){
            this.sizeSt = sizeSt;
            return this;
        }
        public ConditionBuilder sizeLessEqThan(Long sizeEd){
            this.sizeEd = sizeEd;
            return this;
        }


        public ConditionBuilder sizeList(Long ... size){
            this.sizeList = solveNullList(size);
            return this;
        }

        public ConditionBuilder sizeList(List<Long> size){
            this.sizeList = size;
            return this;
        }

        public ConditionBuilder fuzzyPath (List<String> fuzzyPath){
            this.fuzzyPath = fuzzyPath;
            return this;
        }

        public ConditionBuilder fuzzyPath (String ... fuzzyPath){
            this.fuzzyPath = solveNullList(fuzzyPath);
            return this;
        }

        public ConditionBuilder rightFuzzyPath (List<String> rightFuzzyPath){
            this.rightFuzzyPath = rightFuzzyPath;
            return this;
        }

        public ConditionBuilder rightFuzzyPath (String ... rightFuzzyPath){
            this.rightFuzzyPath = solveNullList(rightFuzzyPath);
            return this;
        }

        public ConditionBuilder pathList(String ... path){
            this.pathList = solveNullList(path);
            return this;
        }

        public ConditionBuilder pathList(List<String> path){
            this.pathList = path;
            return this;
        }

        public ConditionBuilder timestampBetWeen(Long timestampSt,Long timestampEd){
            this.timestampSt = timestampSt;
            this.timestampEd = timestampEd;
            return this;
        }

        public ConditionBuilder timestampGreaterEqThan(Long timestampSt){
            this.timestampSt = timestampSt;
            return this;
        }
        public ConditionBuilder timestampLessEqThan(Long timestampEd){
            this.timestampEd = timestampEd;
            return this;
        }


        public ConditionBuilder timestampList(Long ... timestamp){
            this.timestampList = solveNullList(timestamp);
            return this;
        }

        public ConditionBuilder timestampList(List<Long> timestamp){
            this.timestampList = timestamp;
            return this;
        }

        public ConditionBuilder authorBetWeen(Integer authorSt,Integer authorEd){
            this.authorSt = authorSt;
            this.authorEd = authorEd;
            return this;
        }

        public ConditionBuilder authorGreaterEqThan(Integer authorSt){
            this.authorSt = authorSt;
            return this;
        }
        public ConditionBuilder authorLessEqThan(Integer authorEd){
            this.authorEd = authorEd;
            return this;
        }


        public ConditionBuilder authorList(Integer ... author){
            this.authorList = solveNullList(author);
            return this;
        }

        public ConditionBuilder authorList(List<Integer> author){
            this.authorList = author;
            return this;
        }

        public ConditionBuilder isdelBetWeen(Integer isdelSt,Integer isdelEd){
            this.isdelSt = isdelSt;
            this.isdelEd = isdelEd;
            return this;
        }

        public ConditionBuilder isdelGreaterEqThan(Integer isdelSt){
            this.isdelSt = isdelSt;
            return this;
        }
        public ConditionBuilder isdelLessEqThan(Integer isdelEd){
            this.isdelEd = isdelEd;
            return this;
        }


        public ConditionBuilder isdelList(Integer ... isdel){
            this.isdelList = solveNullList(isdel);
            return this;
        }

        public ConditionBuilder isdelList(List<Integer> isdel){
            this.isdelList = isdel;
            return this;
        }

        public ConditionBuilder fuzzyThumbImagePath (List<String> fuzzyThumbImagePath){
            this.fuzzyThumbImagePath = fuzzyThumbImagePath;
            return this;
        }

        public ConditionBuilder fuzzyThumbImagePath (String ... fuzzyThumbImagePath){
            this.fuzzyThumbImagePath = solveNullList(fuzzyThumbImagePath);
            return this;
        }

        public ConditionBuilder rightFuzzyThumbImagePath (List<String> rightFuzzyThumbImagePath){
            this.rightFuzzyThumbImagePath = rightFuzzyThumbImagePath;
            return this;
        }

        public ConditionBuilder rightFuzzyThumbImagePath (String ... rightFuzzyThumbImagePath){
            this.rightFuzzyThumbImagePath = solveNullList(rightFuzzyThumbImagePath);
            return this;
        }

        public ConditionBuilder thumbImagePathList(String ... thumbImagePath){
            this.thumbImagePathList = solveNullList(thumbImagePath);
            return this;
        }

        public ConditionBuilder thumbImagePathList(List<String> thumbImagePath){
            this.thumbImagePathList = thumbImagePath;
            return this;
        }

        public ConditionBuilder fuzzySuffix (List<String> fuzzySuffix){
            this.fuzzySuffix = fuzzySuffix;
            return this;
        }

        public ConditionBuilder fuzzySuffix (String ... fuzzySuffix){
            this.fuzzySuffix = solveNullList(fuzzySuffix);
            return this;
        }

        public ConditionBuilder rightFuzzySuffix (List<String> rightFuzzySuffix){
            this.rightFuzzySuffix = rightFuzzySuffix;
            return this;
        }

        public ConditionBuilder rightFuzzySuffix (String ... rightFuzzySuffix){
            this.rightFuzzySuffix = solveNullList(rightFuzzySuffix);
            return this;
        }

        public ConditionBuilder suffixList(String ... suffix){
            this.suffixList = solveNullList(suffix);
            return this;
        }

        public ConditionBuilder suffixList(List<String> suffix){
            this.suffixList = suffix;
            return this;
        }

        private <T>List<T> solveNullList(T ... objs){
            if (objs != null){
            List<T> list = new ArrayList<>();
                for (T item : objs){
                    if (item != null){
                        list.add(item);
                    }
                }
                return list;
            }
            return null;
        }

        public ConditionBuilder build(){return this;}
    }

    public static class Builder {

        private FileInfo obj;

        public Builder(){
            this.obj = new FileInfo();
        }

        public Builder id(String id){
            this.obj.setId(id);
            return this;
        }
        public Builder filename(String filename){
            this.obj.setFilename(filename);
            return this;
        }
        public Builder size(Long size){
            this.obj.setSize(size);
            return this;
        }
        public Builder path(String path){
            this.obj.setPath(path);
            return this;
        }
        public Builder timestamp(Long timestamp){
            this.obj.setTimestamp(timestamp);
            return this;
        }
        public Builder author(Integer author){
            this.obj.setAuthor(author);
            return this;
        }
        public Builder isdel(Integer isdel){
            this.obj.setIsdel(isdel);
            return this;
        }
        public Builder thumbImagePath(String thumbImagePath){
            this.obj.setThumbImagePath(thumbImagePath);
            return this;
        }
        public Builder suffix(String suffix){
            this.obj.setSuffix(suffix);
            return this;
        }
        public FileInfo build(){return obj;}
    }

}
