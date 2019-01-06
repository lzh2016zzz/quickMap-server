package org.quickMap.dataService.dao.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
*
*  @author author
*/
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1545294972786L;


    /**
    * 主键
    * 
    * isNullAble:0
    */
    private String id;

    /**
    * 
    * isNullAble:0
    */
    private String loginName;

    /**
    * 
    * isNullAble:0
    */
    private String password;

    /**
    * 
    * isNullAble:0
    */
    private String salt;

    /**
    * 
    * isNullAble:1
    */
    private String roles;

    /**
    * 
    * isNullAble:1
    */
    private String nickName;

    /**
    * 
    * isNullAble:1,defaultVal:1
    */
    private Integer isDel;

    /**
    * 
    * isNullAble:1
    */
    private String resetPasswordAnswer;


    public void setId(String id){this.id = id;}

    public String getId(){return this.id;}

    public void setLoginName(String loginName){this.loginName = loginName;}

    public String getLoginName(){return this.loginName;}

    public void setPassword(String password){this.password = password;}

    public String getPassword(){return this.password;}

    public void setSalt(String salt){this.salt = salt;}

    public String getSalt(){return this.salt;}

    public void setRoles(String roles){this.roles = roles;}

    public String getRoles(){return this.roles;}

    public void setNickName(String nickName){this.nickName = nickName;}

    public String getNickName(){return this.nickName;}

    public void setIsDel(Integer isDel){this.isDel = isDel;}

    public Integer getIsDel(){return this.isDel;}

    public void setResetPasswordAnswer(String resetPasswordAnswer){this.resetPasswordAnswer = resetPasswordAnswer;}

    public String getResetPasswordAnswer(){return this.resetPasswordAnswer;}
    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                "loginName='" + loginName + '\'' +
                "password='" + password + '\'' +
                "salt='" + salt + '\'' +
                "roles='" + roles + '\'' +
                "nickName='" + nickName + '\'' +
                "isDel='" + isDel + '\'' +
                "resetPasswordAnswer='" + resetPasswordAnswer + '\'' +
            '}';
    }

    public static Builder Build(){return new Builder();}

    public static ConditionBuilder ConditionBuild(){return new ConditionBuilder();}

    public static UpdateBuilder UpdateBuild(){return new UpdateBuilder();}

    public static QueryBuilder QueryBuild(){return new QueryBuilder();}

    public static class UpdateBuilder {

        private UserInfo set;

        private ConditionBuilder where;

        public UpdateBuilder set(UserInfo set){
            this.set = set;
            return this;
        }

        public UserInfo getSet(){
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

    public static class QueryBuilder extends UserInfo{
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
        private List<String> loginNameList;

        public List<String> getLoginNameList(){return this.loginNameList;}


        private List<String> fuzzyLoginName;

        public List<String> getFuzzyLoginName(){return this.fuzzyLoginName;}

        private List<String> rightFuzzyLoginName;

        public List<String> getRightFuzzyLoginName(){return this.rightFuzzyLoginName;}
        private List<String> passwordList;

        public List<String> getPasswordList(){return this.passwordList;}


        private List<String> fuzzyPassword;

        public List<String> getFuzzyPassword(){return this.fuzzyPassword;}

        private List<String> rightFuzzyPassword;

        public List<String> getRightFuzzyPassword(){return this.rightFuzzyPassword;}
        private List<String> saltList;

        public List<String> getSaltList(){return this.saltList;}


        private List<String> fuzzySalt;

        public List<String> getFuzzySalt(){return this.fuzzySalt;}

        private List<String> rightFuzzySalt;

        public List<String> getRightFuzzySalt(){return this.rightFuzzySalt;}
        private List<String> rolesList;

        public List<String> getRolesList(){return this.rolesList;}


        private List<String> fuzzyRoles;

        public List<String> getFuzzyRoles(){return this.fuzzyRoles;}

        private List<String> rightFuzzyRoles;

        public List<String> getRightFuzzyRoles(){return this.rightFuzzyRoles;}
        private List<String> nickNameList;

        public List<String> getNickNameList(){return this.nickNameList;}


        private List<String> fuzzyNickName;

        public List<String> getFuzzyNickName(){return this.fuzzyNickName;}

        private List<String> rightFuzzyNickName;

        public List<String> getRightFuzzyNickName(){return this.rightFuzzyNickName;}
        private List<Integer> isDelList;

        public List<Integer> getIsDelList(){return this.isDelList;}

        private Integer isDelSt;

        private Integer isDelEd;

        public Integer getIsDelSt(){return this.isDelSt;}

        public Integer getIsDelEd(){return this.isDelEd;}

        private List<String> resetPasswordAnswerList;

        public List<String> getResetPasswordAnswerList(){return this.resetPasswordAnswerList;}


        private List<String> fuzzyResetPasswordAnswer;

        public List<String> getFuzzyResetPasswordAnswer(){return this.fuzzyResetPasswordAnswer;}

        private List<String> rightFuzzyResetPasswordAnswer;

        public List<String> getRightFuzzyResetPasswordAnswer(){return this.rightFuzzyResetPasswordAnswer;}
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

        public QueryBuilder fuzzyLoginName (List<String> fuzzyLoginName){
            this.fuzzyLoginName = fuzzyLoginName;
            return this;
        }

        public QueryBuilder fuzzyLoginName (String ... fuzzyLoginName){
            this.fuzzyLoginName = solveNullList(fuzzyLoginName);
            return this;
        }

        public QueryBuilder rightFuzzyLoginName (List<String> rightFuzzyLoginName){
            this.rightFuzzyLoginName = rightFuzzyLoginName;
            return this;
        }

        public QueryBuilder rightFuzzyLoginName (String ... rightFuzzyLoginName){
            this.rightFuzzyLoginName = solveNullList(rightFuzzyLoginName);
            return this;
        }

        public QueryBuilder loginName(String loginName){
            setLoginName(loginName);
            return this;
        }

        public QueryBuilder loginNameList(String ... loginName){
            this.loginNameList = solveNullList(loginName);
            return this;
        }

        public QueryBuilder loginNameList(List<String> loginName){
            this.loginNameList = loginName;
            return this;
        }

        public QueryBuilder fetchLoginName(){
            setFetchFields("fetchFields","loginName");
            return this;
        }

        public QueryBuilder excludeLoginName(){
            setFetchFields("excludeFields","loginName");
            return this;
        }

        public QueryBuilder fuzzyPassword (List<String> fuzzyPassword){
            this.fuzzyPassword = fuzzyPassword;
            return this;
        }

        public QueryBuilder fuzzyPassword (String ... fuzzyPassword){
            this.fuzzyPassword = solveNullList(fuzzyPassword);
            return this;
        }

        public QueryBuilder rightFuzzyPassword (List<String> rightFuzzyPassword){
            this.rightFuzzyPassword = rightFuzzyPassword;
            return this;
        }

        public QueryBuilder rightFuzzyPassword (String ... rightFuzzyPassword){
            this.rightFuzzyPassword = solveNullList(rightFuzzyPassword);
            return this;
        }

        public QueryBuilder password(String password){
            setPassword(password);
            return this;
        }

        public QueryBuilder passwordList(String ... password){
            this.passwordList = solveNullList(password);
            return this;
        }

        public QueryBuilder passwordList(List<String> password){
            this.passwordList = password;
            return this;
        }

        public QueryBuilder fetchPassword(){
            setFetchFields("fetchFields","password");
            return this;
        }

        public QueryBuilder excludePassword(){
            setFetchFields("excludeFields","password");
            return this;
        }

        public QueryBuilder fuzzySalt (List<String> fuzzySalt){
            this.fuzzySalt = fuzzySalt;
            return this;
        }

        public QueryBuilder fuzzySalt (String ... fuzzySalt){
            this.fuzzySalt = solveNullList(fuzzySalt);
            return this;
        }

        public QueryBuilder rightFuzzySalt (List<String> rightFuzzySalt){
            this.rightFuzzySalt = rightFuzzySalt;
            return this;
        }

        public QueryBuilder rightFuzzySalt (String ... rightFuzzySalt){
            this.rightFuzzySalt = solveNullList(rightFuzzySalt);
            return this;
        }

        public QueryBuilder salt(String salt){
            setSalt(salt);
            return this;
        }

        public QueryBuilder saltList(String ... salt){
            this.saltList = solveNullList(salt);
            return this;
        }

        public QueryBuilder saltList(List<String> salt){
            this.saltList = salt;
            return this;
        }

        public QueryBuilder fetchSalt(){
            setFetchFields("fetchFields","salt");
            return this;
        }

        public QueryBuilder excludeSalt(){
            setFetchFields("excludeFields","salt");
            return this;
        }

        public QueryBuilder fuzzyRoles (List<String> fuzzyRoles){
            this.fuzzyRoles = fuzzyRoles;
            return this;
        }

        public QueryBuilder fuzzyRoles (String ... fuzzyRoles){
            this.fuzzyRoles = solveNullList(fuzzyRoles);
            return this;
        }

        public QueryBuilder rightFuzzyRoles (List<String> rightFuzzyRoles){
            this.rightFuzzyRoles = rightFuzzyRoles;
            return this;
        }

        public QueryBuilder rightFuzzyRoles (String ... rightFuzzyRoles){
            this.rightFuzzyRoles = solveNullList(rightFuzzyRoles);
            return this;
        }

        public QueryBuilder roles(String roles){
            setRoles(roles);
            return this;
        }

        public QueryBuilder rolesList(String ... roles){
            this.rolesList = solveNullList(roles);
            return this;
        }

        public QueryBuilder rolesList(List<String> roles){
            this.rolesList = roles;
            return this;
        }

        public QueryBuilder fetchRoles(){
            setFetchFields("fetchFields","roles");
            return this;
        }

        public QueryBuilder excludeRoles(){
            setFetchFields("excludeFields","roles");
            return this;
        }

        public QueryBuilder fuzzyNickName (List<String> fuzzyNickName){
            this.fuzzyNickName = fuzzyNickName;
            return this;
        }

        public QueryBuilder fuzzyNickName (String ... fuzzyNickName){
            this.fuzzyNickName = solveNullList(fuzzyNickName);
            return this;
        }

        public QueryBuilder rightFuzzyNickName (List<String> rightFuzzyNickName){
            this.rightFuzzyNickName = rightFuzzyNickName;
            return this;
        }

        public QueryBuilder rightFuzzyNickName (String ... rightFuzzyNickName){
            this.rightFuzzyNickName = solveNullList(rightFuzzyNickName);
            return this;
        }

        public QueryBuilder nickName(String nickName){
            setNickName(nickName);
            return this;
        }

        public QueryBuilder nickNameList(String ... nickName){
            this.nickNameList = solveNullList(nickName);
            return this;
        }

        public QueryBuilder nickNameList(List<String> nickName){
            this.nickNameList = nickName;
            return this;
        }

        public QueryBuilder fetchNickName(){
            setFetchFields("fetchFields","nickName");
            return this;
        }

        public QueryBuilder excludeNickName(){
            setFetchFields("excludeFields","nickName");
            return this;
        }

        public QueryBuilder isDelBetWeen(Integer isDelSt,Integer isDelEd){
            this.isDelSt = isDelSt;
            this.isDelEd = isDelEd;
            return this;
        }

        public QueryBuilder isDelGreaterEqThan(Integer isDelSt){
            this.isDelSt = isDelSt;
            return this;
        }
        public QueryBuilder isDelLessEqThan(Integer isDelEd){
            this.isDelEd = isDelEd;
            return this;
        }


        public QueryBuilder isDel(Integer isDel){
            setIsDel(isDel);
            return this;
        }

        public QueryBuilder isDelList(Integer ... isDel){
            this.isDelList = solveNullList(isDel);
            return this;
        }

        public QueryBuilder isDelList(List<Integer> isDel){
            this.isDelList = isDel;
            return this;
        }

        public QueryBuilder fetchIsDel(){
            setFetchFields("fetchFields","isDel");
            return this;
        }

        public QueryBuilder excludeIsDel(){
            setFetchFields("excludeFields","isDel");
            return this;
        }

        public QueryBuilder fuzzyResetPasswordAnswer (List<String> fuzzyResetPasswordAnswer){
            this.fuzzyResetPasswordAnswer = fuzzyResetPasswordAnswer;
            return this;
        }

        public QueryBuilder fuzzyResetPasswordAnswer (String ... fuzzyResetPasswordAnswer){
            this.fuzzyResetPasswordAnswer = solveNullList(fuzzyResetPasswordAnswer);
            return this;
        }

        public QueryBuilder rightFuzzyResetPasswordAnswer (List<String> rightFuzzyResetPasswordAnswer){
            this.rightFuzzyResetPasswordAnswer = rightFuzzyResetPasswordAnswer;
            return this;
        }

        public QueryBuilder rightFuzzyResetPasswordAnswer (String ... rightFuzzyResetPasswordAnswer){
            this.rightFuzzyResetPasswordAnswer = solveNullList(rightFuzzyResetPasswordAnswer);
            return this;
        }

        public QueryBuilder resetPasswordAnswer(String resetPasswordAnswer){
            setResetPasswordAnswer(resetPasswordAnswer);
            return this;
        }

        public QueryBuilder resetPasswordAnswerList(String ... resetPasswordAnswer){
            this.resetPasswordAnswerList = solveNullList(resetPasswordAnswer);
            return this;
        }

        public QueryBuilder resetPasswordAnswerList(List<String> resetPasswordAnswer){
            this.resetPasswordAnswerList = resetPasswordAnswer;
            return this;
        }

        public QueryBuilder fetchResetPasswordAnswer(){
            setFetchFields("fetchFields","resetPasswordAnswer");
            return this;
        }

        public QueryBuilder excludeResetPasswordAnswer(){
            setFetchFields("excludeFields","resetPasswordAnswer");
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

        public UserInfo build(){return this;}
    }


    public static class ConditionBuilder{
        private List<String> idList;

        public List<String> getIdList(){return this.idList;}


        private List<String> fuzzyId;

        public List<String> getFuzzyId(){return this.fuzzyId;}

        private List<String> rightFuzzyId;

        public List<String> getRightFuzzyId(){return this.rightFuzzyId;}
        private List<String> loginNameList;

        public List<String> getLoginNameList(){return this.loginNameList;}


        private List<String> fuzzyLoginName;

        public List<String> getFuzzyLoginName(){return this.fuzzyLoginName;}

        private List<String> rightFuzzyLoginName;

        public List<String> getRightFuzzyLoginName(){return this.rightFuzzyLoginName;}
        private List<String> passwordList;

        public List<String> getPasswordList(){return this.passwordList;}


        private List<String> fuzzyPassword;

        public List<String> getFuzzyPassword(){return this.fuzzyPassword;}

        private List<String> rightFuzzyPassword;

        public List<String> getRightFuzzyPassword(){return this.rightFuzzyPassword;}
        private List<String> saltList;

        public List<String> getSaltList(){return this.saltList;}


        private List<String> fuzzySalt;

        public List<String> getFuzzySalt(){return this.fuzzySalt;}

        private List<String> rightFuzzySalt;

        public List<String> getRightFuzzySalt(){return this.rightFuzzySalt;}
        private List<String> rolesList;

        public List<String> getRolesList(){return this.rolesList;}


        private List<String> fuzzyRoles;

        public List<String> getFuzzyRoles(){return this.fuzzyRoles;}

        private List<String> rightFuzzyRoles;

        public List<String> getRightFuzzyRoles(){return this.rightFuzzyRoles;}
        private List<String> nickNameList;

        public List<String> getNickNameList(){return this.nickNameList;}


        private List<String> fuzzyNickName;

        public List<String> getFuzzyNickName(){return this.fuzzyNickName;}

        private List<String> rightFuzzyNickName;

        public List<String> getRightFuzzyNickName(){return this.rightFuzzyNickName;}
        private List<Integer> isDelList;

        public List<Integer> getIsDelList(){return this.isDelList;}

        private Integer isDelSt;

        private Integer isDelEd;

        public Integer getIsDelSt(){return this.isDelSt;}

        public Integer getIsDelEd(){return this.isDelEd;}

        private List<String> resetPasswordAnswerList;

        public List<String> getResetPasswordAnswerList(){return this.resetPasswordAnswerList;}


        private List<String> fuzzyResetPasswordAnswer;

        public List<String> getFuzzyResetPasswordAnswer(){return this.fuzzyResetPasswordAnswer;}

        private List<String> rightFuzzyResetPasswordAnswer;

        public List<String> getRightFuzzyResetPasswordAnswer(){return this.rightFuzzyResetPasswordAnswer;}

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

        public ConditionBuilder fuzzyLoginName (List<String> fuzzyLoginName){
            this.fuzzyLoginName = fuzzyLoginName;
            return this;
        }

        public ConditionBuilder fuzzyLoginName (String ... fuzzyLoginName){
            this.fuzzyLoginName = solveNullList(fuzzyLoginName);
            return this;
        }

        public ConditionBuilder rightFuzzyLoginName (List<String> rightFuzzyLoginName){
            this.rightFuzzyLoginName = rightFuzzyLoginName;
            return this;
        }

        public ConditionBuilder rightFuzzyLoginName (String ... rightFuzzyLoginName){
            this.rightFuzzyLoginName = solveNullList(rightFuzzyLoginName);
            return this;
        }

        public ConditionBuilder loginNameList(String ... loginName){
            this.loginNameList = solveNullList(loginName);
            return this;
        }

        public ConditionBuilder loginNameList(List<String> loginName){
            this.loginNameList = loginName;
            return this;
        }

        public ConditionBuilder fuzzyPassword (List<String> fuzzyPassword){
            this.fuzzyPassword = fuzzyPassword;
            return this;
        }

        public ConditionBuilder fuzzyPassword (String ... fuzzyPassword){
            this.fuzzyPassword = solveNullList(fuzzyPassword);
            return this;
        }

        public ConditionBuilder rightFuzzyPassword (List<String> rightFuzzyPassword){
            this.rightFuzzyPassword = rightFuzzyPassword;
            return this;
        }

        public ConditionBuilder rightFuzzyPassword (String ... rightFuzzyPassword){
            this.rightFuzzyPassword = solveNullList(rightFuzzyPassword);
            return this;
        }

        public ConditionBuilder passwordList(String ... password){
            this.passwordList = solveNullList(password);
            return this;
        }

        public ConditionBuilder passwordList(List<String> password){
            this.passwordList = password;
            return this;
        }

        public ConditionBuilder fuzzySalt (List<String> fuzzySalt){
            this.fuzzySalt = fuzzySalt;
            return this;
        }

        public ConditionBuilder fuzzySalt (String ... fuzzySalt){
            this.fuzzySalt = solveNullList(fuzzySalt);
            return this;
        }

        public ConditionBuilder rightFuzzySalt (List<String> rightFuzzySalt){
            this.rightFuzzySalt = rightFuzzySalt;
            return this;
        }

        public ConditionBuilder rightFuzzySalt (String ... rightFuzzySalt){
            this.rightFuzzySalt = solveNullList(rightFuzzySalt);
            return this;
        }

        public ConditionBuilder saltList(String ... salt){
            this.saltList = solveNullList(salt);
            return this;
        }

        public ConditionBuilder saltList(List<String> salt){
            this.saltList = salt;
            return this;
        }

        public ConditionBuilder fuzzyRoles (List<String> fuzzyRoles){
            this.fuzzyRoles = fuzzyRoles;
            return this;
        }

        public ConditionBuilder fuzzyRoles (String ... fuzzyRoles){
            this.fuzzyRoles = solveNullList(fuzzyRoles);
            return this;
        }

        public ConditionBuilder rightFuzzyRoles (List<String> rightFuzzyRoles){
            this.rightFuzzyRoles = rightFuzzyRoles;
            return this;
        }

        public ConditionBuilder rightFuzzyRoles (String ... rightFuzzyRoles){
            this.rightFuzzyRoles = solveNullList(rightFuzzyRoles);
            return this;
        }

        public ConditionBuilder rolesList(String ... roles){
            this.rolesList = solveNullList(roles);
            return this;
        }

        public ConditionBuilder rolesList(List<String> roles){
            this.rolesList = roles;
            return this;
        }

        public ConditionBuilder fuzzyNickName (List<String> fuzzyNickName){
            this.fuzzyNickName = fuzzyNickName;
            return this;
        }

        public ConditionBuilder fuzzyNickName (String ... fuzzyNickName){
            this.fuzzyNickName = solveNullList(fuzzyNickName);
            return this;
        }

        public ConditionBuilder rightFuzzyNickName (List<String> rightFuzzyNickName){
            this.rightFuzzyNickName = rightFuzzyNickName;
            return this;
        }

        public ConditionBuilder rightFuzzyNickName (String ... rightFuzzyNickName){
            this.rightFuzzyNickName = solveNullList(rightFuzzyNickName);
            return this;
        }

        public ConditionBuilder nickNameList(String ... nickName){
            this.nickNameList = solveNullList(nickName);
            return this;
        }

        public ConditionBuilder nickNameList(List<String> nickName){
            this.nickNameList = nickName;
            return this;
        }

        public ConditionBuilder isDelBetWeen(Integer isDelSt,Integer isDelEd){
            this.isDelSt = isDelSt;
            this.isDelEd = isDelEd;
            return this;
        }

        public ConditionBuilder isDelGreaterEqThan(Integer isDelSt){
            this.isDelSt = isDelSt;
            return this;
        }
        public ConditionBuilder isDelLessEqThan(Integer isDelEd){
            this.isDelEd = isDelEd;
            return this;
        }


        public ConditionBuilder isDelList(Integer ... isDel){
            this.isDelList = solveNullList(isDel);
            return this;
        }

        public ConditionBuilder isDelList(List<Integer> isDel){
            this.isDelList = isDel;
            return this;
        }

        public ConditionBuilder fuzzyResetPasswordAnswer (List<String> fuzzyResetPasswordAnswer){
            this.fuzzyResetPasswordAnswer = fuzzyResetPasswordAnswer;
            return this;
        }

        public ConditionBuilder fuzzyResetPasswordAnswer (String ... fuzzyResetPasswordAnswer){
            this.fuzzyResetPasswordAnswer = solveNullList(fuzzyResetPasswordAnswer);
            return this;
        }

        public ConditionBuilder rightFuzzyResetPasswordAnswer (List<String> rightFuzzyResetPasswordAnswer){
            this.rightFuzzyResetPasswordAnswer = rightFuzzyResetPasswordAnswer;
            return this;
        }

        public ConditionBuilder rightFuzzyResetPasswordAnswer (String ... rightFuzzyResetPasswordAnswer){
            this.rightFuzzyResetPasswordAnswer = solveNullList(rightFuzzyResetPasswordAnswer);
            return this;
        }

        public ConditionBuilder resetPasswordAnswerList(String ... resetPasswordAnswer){
            this.resetPasswordAnswerList = solveNullList(resetPasswordAnswer);
            return this;
        }

        public ConditionBuilder resetPasswordAnswerList(List<String> resetPasswordAnswer){
            this.resetPasswordAnswerList = resetPasswordAnswer;
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

        private UserInfo obj;

        public Builder(){
            this.obj = new UserInfo();
        }

        public Builder id(String id){
            this.obj.setId(id);
            return this;
        }
        public Builder loginName(String loginName){
            this.obj.setLoginName(loginName);
            return this;
        }
        public Builder password(String password){
            this.obj.setPassword(password);
            return this;
        }
        public Builder salt(String salt){
            this.obj.setSalt(salt);
            return this;
        }
        public Builder roles(String roles){
            this.obj.setRoles(roles);
            return this;
        }
        public Builder nickName(String nickName){
            this.obj.setNickName(nickName);
            return this;
        }
        public Builder isDel(Integer isDel){
            this.obj.setIsDel(isDel);
            return this;
        }
        public Builder resetPasswordAnswer(String resetPasswordAnswer){
            this.obj.setResetPasswordAnswer(resetPasswordAnswer);
            return this;
        }
        public UserInfo build(){return obj;}
    }

}
