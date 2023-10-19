import sys
from collections import defaultdict
sysinput = sys.stdin.readline
def solution():
    N = int(sysinput().strip())
    nums = defaultdict(int)  # 개수
    subs = defaultdict(int)  # z 변환 시 차이
    res = 0  # 10진수로 바꿔 저장
    ans = ''  # 16진수로 바꿔 저장
    for _ in range(N):
        tmp = sysinput().strip()
        for idx in range(len(tmp)):
            nums[tmp[idx]] += 36**(len(tmp)-idx-1)  # 자릿수 고려해 개수 저장
            subs[tmp[idx]] = nums[tmp[idx]]*(int('Z',36)-int(tmp[idx],36))  # Z와 차이 X 개수
    K = int(sysinput().strip())

    list_subs = sorted(subs.items(), key=lambda x: (-x[1], x[0])) # Z 변환 시 차이 내림차순으로 정렬
    K_calc = min(K, len(list_subs)) # index error를 막기 위한 최소값
    for i in range(K_calc):
        res += int('Z',36)*nums[list_subs[i][0]]  # K까지는 Z로 변환한 값으로 계산
    for i in range(K_calc,len(list_subs)):
        res += int(list_subs[i][0], 36) * nums[list_subs[i][0]] # 그 이후에는 원래 수로 계산 

    to_36 = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ'
    while res != 0:
        ans = to_36[res%36] + ans  # 10진수를 36진수로 바꿈
        res //= 36
    if ans == '':
        ans = '0'

    print(ans)

solution()

