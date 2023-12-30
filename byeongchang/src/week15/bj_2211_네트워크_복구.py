import sys
from collections import defaultdict
from heapq import heappush, heappop

# https://www.acmicpc.net/problem/2211

def solution() -> None:
    sysinput = sys.stdin.readline
    N, M = map(int, sysinput().split())
    network = defaultdict(dict)
    for _ in range(M):
        a, b, c = map(int, sysinput().split())
        network[a][b] = c
        network[b][a] = c

    circuit = dijkstra(1, network, N)
    print(N-1)
    for i in range(2, N+1):
        print(i, circuit[i])


def dijkstra(start: int, network: defaultdict, N: int) -> dict:
    dist = dict()
    circuit = dict()
    pq = []
    dist[start] = 0
    heappush(pq, (0, start))
    while pq:
        w, v = heappop(pq)
        if w > dist[v]:
            continue
        for u, w in network[v].items():
            if u not in dist or dist[u] > dist[v] + w:
                dist[u] = dist[v] + w
                heappush(pq, (dist[u], u))
                circuit[u] = v

    return circuit


def solution2() -> None:
    sysinput = sys.stdin.readline
    N, M = map(int, sysinput().split())
    network = [[0] * (N+1) for _ in range(N+1)]
    for _ in range(M):
        a, b, c = map(int, sysinput().split())
        network[a][b] = c
        network[b][a] = c

    circuit = dijkstra2(1, network, N)
    print(N-1)
    for i in range(2, N+1):
        print(i, circuit[i])


def dijkstra2(start: int, network: list, N: int) -> list:
    circuit = [0] * (N+1)
    dist = [int(10e9)] * (N+1)
    pq = []
    dist[start] = 0
    heappush(pq, (0, start))
    while pq:
        w, v = heappop(pq)
        for u, uw in enumerate(network[v]):
            if uw == 0:
                continue
            if dist[u] > w + uw:
                dist[u] = w + uw
                heappush(pq, (dist[u], u))
                circuit[u] = v

    return circuit


solution()