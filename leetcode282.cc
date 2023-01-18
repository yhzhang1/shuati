class Solution {
public:
  vector<string> addOperators(string num, int target) {
    vector<string> ans;
    string exp(num.length() * 2, '\0');    
    dfs(num, target, 0, exp, 0, 0, 0, &ans);
    return ans;
  }
private:
  void dfs(const string& num, const int target,
           int pos, string& exp, int len, long prev, long curr,
           vector<string>* ans) {    
    // len: 初始表达式的长度
    if (pos == num.length()) {      
      if (curr == target) ans->push_back(exp.substr(0, len));
      return;
    }
    
    long n = 0;
    int s = pos;
    int l = len;
    if (s != 0) ++len;    
    while (pos < num.size()) {      
      n = n * 10 + (num[pos] - '0');
      if (num[s] == '0' && pos - s > 0) break; // 0X... invalid number
      if (n > INT_MAX) break; // too long
      exp[len++] = num[pos++];
      if (s == 0) {
        dfs(num, target, pos, exp, len, n, n, ans);
        continue;
      }
      exp[l] = '+'; dfs(num, target, pos, exp, len, n, curr + n, ans);
      exp[l] = '-'; dfs(num, target, pos, exp, len, -n, curr - n, ans);
      exp[l] = '*'; dfs(num, target, pos, exp, len, prev * n, curr - prev + prev * n, ans);
    }
  }
};
// Author: Huahua
// Running time: 128 ms (<79.97%)
class Solution {
public:
  vector<string> addOperators(string num, int target) {
    vector<string> ans;
    dfs(num, target, 0, "", 0, 0, &ans);
    return ans;
  }
private:
  void dfs(const string& num, const int target,  // input
           int pos, const string& exp, long prev, long curr, // state
           vector<string>* ans) {  // output
    //pos: 从哪个字符后面开始取, exp: 当前表达式， prev: 上一个节点的值是多少，curr:如果evaluate当前表达式它的值是多少
    if (pos == num.length()) {
      if (curr == target) ans->push_back(exp);
      return;
    }
    
    for (int l = 1; l <= num.size() - pos; ++l) {
      string t = num.substr(pos, l);    
      if (t[0] == '0' && t.length() > 1) break; // 0X...
      long n = std::stol(t);
      if (n > INT_MAX) break;
      if (pos == 0) {
        dfs(num, target, l, t, n, n, ans);
        continue;
      }
      dfs(num, target, pos + l, exp + '+' + t, n, curr + n, ans);
      dfs(num, target, pos + l, exp + '-' + t, -n, curr - n, ans);
      dfs(num, target, pos + l, exp + '*' + t, prev * n, curr - prev + prev * n, ans);
    }    
  }
};