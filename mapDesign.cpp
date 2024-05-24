#include <iostream>
#include<vector>

using namespace std;

int main() {
    const int n = 3;
    const int m = 5;
    int map[n][m] = { {1, 1, 1, 1, 1},
                      {1, 0, 1, 0, 1},
                      {1, 1, 1, 0, 1} };
    double w = 100, h= 100;
    vector<pair<pair<int, int>, pair<int, int>>> sol;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (map[i][j] != 1)
                continue;
            int min_depth = INT_MAX;
            int width = m;
            for (int k = j; k < m; k++) {
                if (map[i][k] != 1) {
                    width = k;
                    break;
                }
                int depth = 0;
                for (int l = i; l < n; l++) {
                    if (map[l][k] == 1)
                        depth++;
                    else
                        break;
                }
                if (depth < min_depth) {
                    min_depth = depth;
                }
            }
            for (int k = i; k < i + min_depth; k++) {
                for (int l = j; l < width; l++) {
                    map[k][l] = -1;
                }
            }
            sol.push_back({ {i, j}, {i + min_depth - 1, width - 1} });
        }
    }

    for (int i = 0; i < sol.size(); i++) {
        double y1 = sol[i].first.first;
        double x1 = sol[i].first.second;
        double y2 = sol[i].second.first;
        double x2 = sol[i].second.second;
        double cX = (x1 + x2 + 1) * w / 2;
        double cY = (n - (y1 + y2 + 1) / 2) * h;
        double rW = (x2 - x1 + 1) * w;
        double rY = (y2 - y1 + 1) * h;
       // cout << sol[i].first.first << " " << sol[i].first.second << " ";
        //cout << sol[i].second.first << " " << sol[i].second.second << endl;
        printf("this.monsterList.add(new Monster(new Vector(%f, %f), %f, %f));\n", cX, cY, rW, rY);
    }
}
