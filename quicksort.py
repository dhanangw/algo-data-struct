from typing import List


def partition(array: List[int], low: int, high: int) -> List[int]:
    """core operation of quicksort"""
    # 'i' keeps track of position for swapping with pivot.
    i = low - 1
    # 'pivot' serves as value to be compared to.
    pivot = array[high]

    # 'j' loop through 'array' from 'low' until ('high' - 1).
    for j in range(low, high):
        if array[j] < pivot:
            i += 1
            # swap array[j] with array[i]
            temp = array[j]
            array[j] = array[i]
            array[i] = temp
            # array[i],array[j] = array[j],array[i] 
    
    # swap array[i+1] with pivot (array[high])
    array[high] = array[i+1]
    array[i+1] = pivot
    # array[high], array[i+1] = array[i+1], array[high]

    # return position of pivot
    return i + 1

def quicksort(array: List[int], low: int, high: int) -> List[int]:
    if low < high:
        pivot = partition(array, low, high)
        # sort left of pivot
        quicksort(array, low, pivot-1)
        # sort right of pivot
        quicksort(array, pivot+1, high)


array = [19,5,2,32,3,1]
print(f'initial array: {array}')
quicksort(array, 0, len(array) - 1)
print(f'sorted array: {array}')