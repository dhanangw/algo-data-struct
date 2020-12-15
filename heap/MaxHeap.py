from typing import Optional, NoReturn


class MaxHeap:
    def __init__(self, heap_size):
        self.size = 0
        self.heap = [None] * heap_size
        self.capacity = heap_size

    def get_parent_index(self, index: int) -> int:
        return int(index / 2)

    def get_left_child_index(self, index: int) -> int:
        return (index * 2) + 1

    def get_right_child_index(self, index: int) -> int:
        return (index * 2) + 2

    def get_parent_value(self, index: int) -> int:
        return self.heap[self.get_parent_index(index)]

    def get_left_child_value(self, index: int) -> int:
        return self.heap[self.get_left_child_index(index)]

    def get_right_child_value(self, index: int) -> int:
        return self.heap[self.get_right_child_index(index)]

    def has_parent(self, index: int) -> bool:
        return self.heap[self.get_parent_index(index)] != None

    def has_left_child(self, index: int) -> bool:
        return self.heap[self.get_left_child_index(index)] != None

    def has_right_child(self, index: int) -> bool:
        return self.heap[self.get_right_child_index(index)] != None

    def swap(self, index1: int, index2: int) -> NoReturn:
        temp = self.heap[index1]
        self.heap[index1] = self.heap[index2]
        self.heap[index2] = temp

    def ensure_extra_capacity(self) -> NoReturn:
        if self.size == self.capacity:
            self.heap += [None] * self.capacity
            self.capacity *= 2

    def peek(self) -> Optional[int]:
        """returns max element in heap without deleting it from heap."""
        result = None
        if self.size != 0:
            result = self.heap[0]
        return result

    def poll(self) -> int:
        """returns max element in heap and deletes it from heap."""
        result = self.heap[0]
        self.size -= 1
        self.heap[0] = self.heap[self.size]
        self.heapify_down()
        return result 

    def insert(self, value: int) -> NoReturn:    
        self.ensure_extra_capacity()
        self.heap[self.size] = value
        self.size += 1
        self.heapify_up()

    def heapify_up(self) -> NoReturn:
        index = self.size - 1
        while(self.has_parent(index) and self.get_parent_value(index) < self.heap[index]):
            parent_index = self.get_parent_index(index)
            self.swap(parent_index, index)
            index = parent_index

    def heapify_down(self):
        index = 0
        while(self.has_left_child(index)):
            bigger_child_index = self.get_left_child_index(index)

            if self.has_right_child(index) and self.get_right_child_value(index) < self.heap[index]:
                bigger_child_index = self.get_right_child_index(index)
            
            if self.heap[bigger_child_index] < self.heap[index]:
                break

            self.swap(bigger_child_index, index)
            index = bigger_child_index


if __name__ == "__main__":
    heap = MaxHeap(10)
    heap.insert(10)
    print(heap.heap, heap.size)
    heap.insert(15)
    print(heap.heap, heap.size)
    heap.insert(20)
    print(heap.heap, heap.size)
    heap.insert(17)
    heap.insert(100)
    print(heap.peek())
    print(heap.heap, heap.size)
    max = heap.poll()
    print(heap.heap, heap.size)
    print(max)