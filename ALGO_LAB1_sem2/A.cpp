#include <iostream>
#include <vector>

using namespace std;
int n, x, y, a0, m, t, z;
long long b0;

long long pref_sum(long long arr[], int l, int r) {
    if (l == 0) {
        return arr[r];
    }
    return arr[r] - arr[l - 1];
}

long long mod (long long x, int y) {
    if (x >= 0) {
        return x % y;
    }
    return x + y;
}

int main() {
    cin >> n >> x >> y >> a0 >> m >> z >> t >> b0;
    int c[2 * m];
    long long pref_a[n];
    c[0] = mod(b0, n);
    pref_a[0] = a0;
    for (int i = 1; i < 2 * m; i++) {
        b0 = mod((z * b0 + t), 1073741824);
        c[i] = mod(b0, n);
    }
    for (int i = 1; i < n; i++) {
        a0 = mod((x * a0 + y), 65536);
        pref_a[i] = pref_a[i - 1] + a0;
    }
    long long sum = 0;
    int l, r;
    for (int i = 0; i < m; i++) {
        l = min((c[2 * i]), (c[2 * i + 1]));
        r = max(c[2 * i], c[2 * i + 1]);

        sum += pref_sum(pref_a, l, r);

    }
    cout << sum << endl;
}
