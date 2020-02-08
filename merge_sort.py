from typing import List


def merge(left: List[int] = [1, 8], right: List[int] = [4, 7]):
    """
    merge 2 sorted arrays/lists `L` and `R` to `array`.
    """
    nL = len(left)
    nR = len(right)

    i = 0
    j = 0
    array = []

    # do finger-pointing merge.
    # iterate until we exhausts nL or nR.
    while i < nL and j < nR:
        if left[i] <= right[j]:
            array.append(left[i])
            left[i] = 0
            i += 1
        else:
            array.append(right[j])
            right[j] = 0
            j += 1

    # copy remaining elements of nL to array if any.
    if not all(i == 0 for i in left):
        for i in left:
            if i != 0:
                array.append(i)

    # copy remaining elements of nR to array if any.
    if not all(j == 0 for j in right):
        for j in right:
            if j != 0:
                array.append(j)

    return array


def merge_sort(array: List[int]) -> List[int]:
    n = len(array)

    # exit the recursion when length of array is 1.
    if n < 2:
        return array

    # split array by `mid` point.
    mid = int(n/2)

    left = []
    right = []

    for i in range(0, mid):
        left.append(array[i])

    for i in range(mid, n):
        right.append(array[i])

    left = merge_sort(left)
    right = merge_sort(right)
    return merge(left, right)


if __name__ == '__main__':
    array = [1, 2, 3, 4, 5]
    print('unsorted: {}'.format(array))
    print('sorted: {}'.format(merge_sort(array)))
