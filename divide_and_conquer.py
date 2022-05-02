"""exercises 4.X from Grokking Algorithm."""
from typing import List


def sum(array: List[int]) -> int:
    """4.1 Sum all element in array with divide and conquer."""
    # base case.
    if not array:
        return 0
    elif len(array) == 1:
        return array[0]

    # recursive case.
    # input in recursive case should be less than before.
    return array[0] + sum(array[1:])

def count(array: List[int]) -> int:
    """4.2 count number of items in a list."""
    # base case
    if len(array) == 1:
        return 1
    
    # recursive case.
    # input in recursive case should be less than before.
    return 1 + count(array[1:])

def find_max(array: List[int]) -> int:
    """4.3 find maximum number in a list."""
    # base case
    if len(array) == 1:
        return array[0]

    # recursive case
    next_max = find_max(array[1:])
    if array[0] > next_max:
        return array[0]
    else:
        return next_max

def binary_search(
    array: List[int],
    left: int,
    right: int,
    target: int
) -> bool:
    """4.4 implement binary search."""
    # base case
    mid = int(left + ((right - left) / 2)) #TODO fix this formula (target = 7)
    print(f"mid: {mid}")
    if array[mid] == target:
        return True
    elif left == right:
        return False

    # recursive case
    if array[mid] > target:
        right = mid
    elif array[mid] < target:
        left = mid

    return binary_search(array, left, right, target)
    
    

if __name__ == "__main__":
    array = [2,5,3,6,7]
    # print(sum(array))
    # print(count(array))
    # print(find_max(array))
    exists_index = binary_search(
        array=sorted(array),
        left=0,
        right=len(array) -1,
        target=7
    )
    print(exists_index)
