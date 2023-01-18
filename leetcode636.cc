//https://www.bilibili.com/video/BV1dt411K7BS?from=search&seid=7164797290521415358&spm_id_from=333.337.0.0
class Solution {
public:
    void solve(const string &s, int &f, int &se, int &t){
        string l = s.substr(0, s.find(":"));
        f = stoi(l);
        l = s.substr(s.find(":")+1);
        string r = l.substr(l.find(":")+1);
        t = stoi(r);
        if(l[0] == 's') se = 0;
        else se = 1;
    }
    vector<int> exclusiveTime(int n, vector<string>& logs) {
        vector<int> ans(n, 0);
        stack<int> st; //timestamp stack
        stack<int> stf;//function id stack
        for(string s:logs){
            int f, se, t; 
            // f: function id, se: start or end, t: timestamp
            solve(s, f, se, t);
            if(se == 0){
                // 进栈
                ans[f] -= t;
                stf.push(f);
                st.push(t);
            } else {
                // 弹栈
                ans[f] += t+1;
                int s = st.top(); st.pop(); stf.pop(); //s: start timestamp
                if(!stf.empty())
                    ans[stf.top()] -= t-s+1;
            }
        }
        return ans;
    }
};