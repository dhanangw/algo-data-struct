"""
ID: dhanangw
LANG: PYTHON3
TASK: ride
"""
import sys
sys.stderr.write('message')

numbers = {
    "A": 1,
    "B": 2,
    "C": 3,
    "D": 4,
    "E": 5,
    "F": 6,
    "G": 7,
    "H": 8,
    "I": 9,
    "J": 10,
    "K": 11,
    "L": 12,
    "M": 13,
    "N": 14,
    "O": 15,
    "P": 16,
    "Q": 17,
    "R": 18,
    "S": 19,
    "T": 20,
    "U": 21,
    "V": 22,
    "W": 23,
    "X": 24,
    "Y": 25,
    "Z": 26
}

# input
with open('ride.in', 'r') as fin:
    lines = fin.readlines()

group = lines[0].strip('\n')
comet = lines[1].strip('\n')


def find_mod_result(string: str) -> int:
    mod_result = 1
    for letter in string:
        mod_result = mod_result * numbers[letter]
    mod_result = mod_result % 47
    return mod_result


answer = "STAY"
if find_mod_result(group) == find_mod_result(comet):
    answer = "GO"


with open('ride.out', 'w') as fout:
    fout.write(answer + '\n')
