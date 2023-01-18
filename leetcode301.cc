//https://www.bilibili.com/video/BV1Ct411F7am?from=search&seid=8384852795744314722&spm_id_from=333.337.0.0

class Solution {
public:
    unordered_set<string> ans;
    string s;
    int n;
    int m; //最终剩下来的左右括号有m个
    void dfs(int cur, int l, int r, int dl, int dr, string ns){
        //dl: available left parenthesis to delete
        //dr: available right parenthesis to delete
        if(cur==n){
            ans.insert(ns);
            return;
        }
        if(s[cur]=='('){
            if(dl)  dfs(cur+1, l, r, dl-1, dr, ns); //删除
            if(l < m) dfs(cur+1, l+1, r, dl, dr, ns+'('); //留下
        }
        else if(s[cur]==')'){
            if(dr)  dfs(cur+1, l, r, dl, dr-1, ns); //删除当前右括号
            if(l > r)   dfs(cur+1, l, r+1, dl, dr, ns+')'); //留下右括号
        }
        else // 当前是个字母
            dfs(cur+1, l, r, dl, dr, ns+s[cur]);
    }
    vector<string> removeInvalidParentheses(string s) {
        this->s=s;
        n=s.length();
        m=0;
        int dl=0, dr=0; // available left, right parenthesis to delete
        for(char c:s){
            if(c=='(')  dl++;
            else if(c==')'){
                if(dl)  dl--, m++;
                else    dr++;
            }
        }
        dfs(0,0,0,dl,dr,"");
        vector<string> an;
        for(string i:ans)
            an.push_back(i);
        return an;
    }
};

class Solution {
public:
    set<string> ans;
    string s;
    int n;
    void dfs(int cur, int cnt, string ns){
        // cnt: 多少个左括号可以匹配
        if(cur == n){
            if(cnt == 0)
                ans.insert(ns);
            return;
        }
        if(s[cur] == '('){
            dfs(cur+1,cnt,ns); // 左括号删掉
            dfs(cur+1,cnt+1,ns+'('); //左括号留下
        }
        else if(s[cur] == ')'){
            dfs(cur+1, cnt, ns); // 右括号删掉
            if(cnt) dfs(cur+1, cnt-1, ns+')'); // 右括号留下
        }
        else
            dfs(cur+1, cnt, ns+s[cur]); //字母 无脑保留
    }
    vector<string> removeInvalidParentheses(string s) {
        this->s=s;
        n = s.length();
        dfs(0,0,"");
        int maxl=0; //最终可以保留的最大长度
        for(string i:ans)
            maxl=max(maxl,(int)i.length());
        vector<string> an;
        for(string i:ans)
            if(i.length()==maxl)    an.push_back(i);
        return an;
    }
};