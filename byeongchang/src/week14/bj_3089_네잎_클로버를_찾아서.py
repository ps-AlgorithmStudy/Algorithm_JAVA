import sys
from collections import defaultdict
from bisect import bisect_left, bisect_right
sysinput = sys.stdin.readline

# https://www.acmicpc.net/problem/3089
def solution():
    N, M = map(int, sysinput().strip().split())
    clover_x = defaultdict(list)
    clover_y = defaultdict(list)
    for _ in range(N):
        x, y = map(int, sysinput().strip().split())
        clover_x[x].append(y)
        clover_y[y].append(x)
    orders = list(sysinput().strip())

    for idx in clover_x:
        clover_x[idx].sort()
    for idx in clover_y:
        clover_y[idx].sort()
    
    pos_x, pos_y = 0, 0
    for order in orders:
        if order == 'U':
            idx = bisect_right(clover_x[pos_x], pos_y)
            pos_y = clover_x[pos_x][idx]
            pass
        elif order == 'D':
            idx = bisect_left(clover_x[pos_x], pos_y) - 1     # bisect_left는 찾는 값이 있는 경우 해당 값을 오른쪽으로 밀어버리고 그 위치를 반환하기 때문에 -1 처리해야 함
            pos_y = clover_x[pos_x][idx]
            pass
        elif order == 'L':
            idx = bisect_left(clover_y[pos_y], pos_x) - 1
            pos_x = clover_y[pos_y][idx]
            pass
        elif order == 'R':
            idx = bisect_right(clover_y[pos_y], pos_x)
            pos_x = clover_y[pos_y][idx]
            pass

    print(pos_x, pos_y)


solution()