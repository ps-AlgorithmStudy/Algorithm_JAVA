# https://school.programmers.co.kr/learn/courses/30/lessons/43238
# pg_43238_입국심사

def solution(n, times, **kwargs):  # n: 사람 수, times: 심사관들이 심사하는 시간
    answer = 0
    left = 1
    right = min(times)*n  # 상한선
    while (left <= right) :
        mid = (left + right) // 2
        reviewable_people = 0  
        for t in times :
            reviewable_people += mid // t # mid 시간동안 심사 가능한 최대 인원
            if reviewable_people >= n : # 이미 n명 이상 심사가 가능한 경우면 더 확인하지 않음
                break

        # 심사가능한 인원수가 n을 넘었다는 것은 시간이 남는다는 뜻
        # mid ~ right 까지의 시간은 확인할 필요 없음
        if reviewable_people >= n :
            answer = mid
            right = mid - 1
        # 심사가능한 인원수가 n보다 작다는 것은 시간이 부족하다는 뜻
        # left ~ mid 까지의 시간은 확인할 필요 없음
        else:                       
            left = mid + 1

    return answer






# test
def test():
    tc1 = {
        'n': 6,
        'times': [7, 10],
        'return': 28
    }
    tc2 = {
        'n': 7,
        'times': [2,3],
        'return': 9
    }
    tc = [tc1, tc2]
    for t in tc:
        print('sol:', solution(**t), 'res:', t['return'])


test()