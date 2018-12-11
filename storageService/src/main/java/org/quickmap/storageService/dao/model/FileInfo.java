package org.quickmap.storageService.dao.model;
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

    private static final long serialVersionUID = 1544518570503L;


    /**
    * 主键
    * 
    * isNullAble:0
    */
    private Integer id;

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
    private Integer timestamp;

    /**
    * 
    * isNullAble:1
    */
    private Integer author;


    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public void setFilename(String filename){
        this.filename = filename;
    }

    public String getFilename(){
        return this.filename;
    }

    public void setSize(Long size){
        this.size = size;
    }

    public Long getSize(){
        return this.size;
    }

    public void setPath(String path){
        this.path = path;
    }

    public String getPath(){
        return this.path;
    }

    public void setTimestamp(Integer timestamp){
        this.timestamp = timestamp;
    }

    public Integer getTimestamp(){
        return this.timestamp;
    }

    public void setAuthor(Integer author){
        this.author = author;
    }

    public Integer getAuthor(){
        return this.author;
    }
    @Override
    public String toString() {
        return "FileInfo{" +
                "id='" + id + '\'' +
                "filename='" + filename + '\'' +
                "size='" + size + '\'' +
                "path='" + path + '\'' +
                "timestamp='" + timestamp + '\'' +
                "author='" + author + '\'' +
            '}';
    }

    public static QueryBuilder QueryBuild(){
        return new QueryBuilder();
    }

    public static class QueryBuilder extends FileInfo{
        /**
        * 需要返回的列
        */
        private Map<String,Object> fetchFields;

        public Map<String,Object> getFetchFields(){
            return this.fetchFields;
        }

        private List<Integer> idList;

        private Integer idSt;

        private Integer idEd;

        public Integer getIdSt(){
            return this.idSt;
        }

        public Integer getIdEd(){
            return this.idEd;
        }

        private List<String> filenameList;


        private List<String> fuzzyFilename;

        public List<String> getFuzzyFilename(){
            return this.fuzzyFilename;
        }

        private List<String> rightFuzzyFilename;

        public List<String> getRightFuzzyFilename(){
            return this.rightFuzzyFilename;
        }
        private List<Long> sizeList;

        private Long sizeSt;

        private Long sizeEd;

        public Long getSizeSt(){
            return this.sizeSt;
        }

        public Long getSizeEd(){
            return this.sizeEd;
        }

        private List<String> pathList;


        private List<String> fuzzyPath;

        public List<String> getFuzzyPath(){
            return this.fuzzyPath;
        }

        private List<String> rightFuzzyPath;

        public List<String> getRightFuzzyPath(){
            return this.rightFuzzyPath;
        }
        private List<Integer> timestampList;

        private Integer timestampSt;

        private Integer timestampEd;

        public Integer getTimestampSt(){
            return this.timestampSt;
        }

        public Integer getTimestampEd(){
            return this.timestampEd;
        }

        private List<Integer> authorList;

        private Integer authorSt;

        private Integer authorEd;

        public Integer getAuthorSt(){
            return this.authorSt;
        }

        public Integer getAuthorEd(){
            return this.authorEd;
        }

        private QueryBuilder (){
            this.fetchFields = new HashMap<>();
        }


        public QueryBuilder idBetWeen(Integer idSt,Integer idEd){
            this.idSt = idSt;
            this.idEd = idEd;
            return this;
        }

        public QueryBuilder idGreaterEqThan(Integer idSt){
            this.idSt = idSt;
            return this;
        }
        public QueryBuilder idLessEqThan(Integer idEd){
            this.idEd = idEd;
            return this;
        }


        public QueryBuilder id(Integer id){
            setId(id);
            return this;
        }

        public QueryBuilder idList(Integer ... id){
            if (id != null){
                List<Integer> list = new ArrayList<>();
                for (Integer item : id){
                    if (item != null){
                        list.add(item);
                    }
                }
                this.idList = list;
            }

            return this;
        }

        public QueryBuilder idList(List<Integer> id){
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
            if (fuzzyFilename != null){
                List<String> list = new ArrayList<>();
                for (String item : fuzzyFilename){
                    if (item != null){
                        list.add(item);
                    }
                }
                this.fuzzyFilename = list;
            }
            return this;
        }

        public QueryBuilder rightFuzzyFilename (List<String> rightFuzzyFilename){
            this.rightFuzzyFilename = rightFuzzyFilename;
            return this;
        }

        public QueryBuilder rightFuzzyFilename (String ... rightFuzzyFilename){
            if (rightFuzzyFilename != null){
                List<String> list = new ArrayList<>();
                for (String item : rightFuzzyFilename){
                    if (item != null){
                        list.add(item);
                    }
                }
                this.rightFuzzyFilename = list;
            }
            return this;
        }

        public QueryBuilder filename(String filename){
            setFilename(filename);
            return this;
        }

        public QueryBuilder filenameList(String ... filename){
            if (filename != null){
                List<String> list = new ArrayList<>();
                for (String item : filename){
                    if (item != null){
                        list.add(item);
                    }
                }
                this.filenameList = list;
            }

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
            if (size != null){
                List<Long> list = new ArrayList<>();
                for (Long item : size){
                    if (item != null){
                        list.add(item);
                    }
                }
                this.sizeList = list;
            }

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
            if (fuzzyPath != null){
                List<String> list = new ArrayList<>();
                for (String item : fuzzyPath){
                    if (item != null){
                        list.add(item);
                    }
                }
                this.fuzzyPath = list;
            }
            return this;
        }

        public QueryBuilder rightFuzzyPath (List<String> rightFuzzyPath){
            this.rightFuzzyPath = rightFuzzyPath;
            return this;
        }

        public QueryBuilder rightFuzzyPath (String ... rightFuzzyPath){
            if (rightFuzzyPath != null){
                List<String> list = new ArrayList<>();
                for (String item : rightFuzzyPath){
                    if (item != null){
                        list.add(item);
                    }
                }
                this.rightFuzzyPath = list;
            }
            return this;
        }

        public QueryBuilder path(String path){
            setPath(path);
            return this;
        }

        public QueryBuilder pathList(String ... path){
            if (path != null){
                List<String> list = new ArrayList<>();
                for (String item : path){
                    if (item != null){
                        list.add(item);
                    }
                }
                this.pathList = list;
            }

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



        public QueryBuilder timestampBetWeen(Integer timestampSt,Integer timestampEd){
            this.timestampSt = timestampSt;
            this.timestampEd = timestampEd;
            return this;
        }

        public QueryBuilder timestampGreaterEqThan(Integer timestampSt){
            this.timestampSt = timestampSt;
            return this;
        }
        public QueryBuilder timestampLessEqThan(Integer timestampEd){
            this.timestampEd = timestampEd;
            return this;
        }


        public QueryBuilder timestamp(Integer timestamp){
            setTimestamp(timestamp);
            return this;
        }

        public QueryBuilder timestampList(Integer ... timestamp){
            if (timestamp != null){
                List<Integer> list = new ArrayList<>();
                for (Integer item : timestamp){
                    if (item != null){
                        list.add(item);
                    }
                }
                this.timestampList = list;
            }

            return this;
        }

        public QueryBuilder timestampList(List<Integer> timestamp){
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
            if (author != null){
                List<Integer> list = new ArrayList<>();
                for (Integer item : author){
                    if (item != null){
                        list.add(item);
                    }
                }
                this.authorList = list;
            }

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

        public FileInfo build(){
            return this;
        }
    }

}
