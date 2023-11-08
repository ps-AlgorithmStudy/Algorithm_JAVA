# bj_16724_피리_부는_사나이
import sys
sysinput = sys.stdin.readline

def solution() :
  N, M = map(int, sysinput().strip().split(' '))
  zone = list()
  for idx in range(N):
    zone.append(list(sysinput().strip()))
  visited = [[0]*M for i in range(N)]
  answer = 0
  direc = {'D': [1,0],
           'L': [0,-1],
           'U': [-1,0],
           'R': [0,1]}
  
  turn = 1
  for i in range(N):
    for j in range(M):
      if(visited[i][j] != 0):
        continue
      answer += move(i,j,turn,zone,visited,direc)
      turn += 1
  print(answer)


def move(x: int, y: int, turn: int, zone: list, visited: list, direc: dict) -> int:
  while(True):
    visited[x][y] = turn
    d = direc[zone[x][y]]
    nx = x + d[0]
    ny = y + d[1]
    if(visited[nx][ny] == turn): break
    if(visited[nx][ny] != 0): return 0
    x = nx
    y = ny 
  return 1

solution()