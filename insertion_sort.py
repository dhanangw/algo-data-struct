from typing import List


# implemented from psudocode taken from p.18
def insertion_sort_ascending(array: List[int]) -> List[int]:
    for j in range(1, len(array)):
        key = array[j]
        i = j - 1
        while i >= 0 and array[i] > key:
            array[i+1] = array[i]
            i = i - 1
        array[i+1] = key

    return array


# exercise 2.1-2 p.22
def insertion_sort_descending(array: List[int]) -> List[int]:
    for j in range(1, len(array)):
        key = array[j]
        i = j - 1
        while i >= 0 and array[i] < key:
            array[i+1] = array[i]
            i = i - 1
        array[i+1] = key

    return array


if __name__ == "__main__":
    array = [5, 2, 4, 6, 1, 3]
    print(insertion_sort_ascending(array))
    print(insertion_sort_descending(array))
