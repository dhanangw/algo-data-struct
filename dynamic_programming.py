"""From https://www.youtube.com/watch?v=oBt53YbR9Kk.

Memoization guide:
1. Draw the recursion tree
2. 'Make it work'
3. 'Make it fast'
"""
from typing import Dict, List, Optional


def fibonacci(n: int) -> int:
    """Returns n-th fibonacci number.

    Uses regular recursion.

    Example:
        fib(0) returns 0.
        fib(1) returns 1.
        fib(2) returns 1.
        fib(3) returns 2.
        fib(4) returns 3.
        fib(5) returns 5.
        fib(6) returns 8.

    Time Complexity:
        O(2^n) for each recursive level, we call fibonacci() twice.
    Space Complexity:
        O(n) maximum recursion depth is n.
    """
    if n == 0:
        return 0
    elif n in {1, 2}:
        return 1

    return fibonacci(n - 1) + fibonacci(n - 2)

def fibonacci_memo(n: int, memo: Dict[int, int] = {}) -> int:
    """Returns n-th fibonacci number.

    Uses dynamic programming memoization.

    "Memo" here is a Python dictionary.
    All the recursive calls modify the same dictionary ('memo' object has the same ID in the whole call stack).
    That allows to not explicitly return memo in the function.
    More info: https://www.geeksforgeeks.org/is-python-call-by-reference-or-call-by-value/

    Time Complexity:
        O(n) we don't need to go further into recursive stack if n is already in memo.
    Space Complexity:
        O(n) maximum recursion depth is n.
    """
    if n == 0:
        return 0
    elif n in memo:
        return memo[n]
    elif n in {1,2}:
        return 1

    print(f"before recursion; n: {n}, memo: {memo}, memoID: {id(memo)}")
    memo[n] = fibonacci_memo(n - 1, memo) + fibonacci_memo(n - 2, memo)
    print(f"after recursion; n: {n}, memo: {memo}, memoID: {id(memo)}")

    return memo[n]

def grid_traveler(m: int, n: int) -> int:
    """Given m*n grid, return number of possible way to traverse the grid from top-left cell to bottom-right cell.
    You can only move downward or rightward.

    Uses normal recursion.

    Example:
        grid_traveler(2,3) returns 3.
        grid_traveler(1,1) returns 0.
        grid_traveler(3,3) returns 6.

    Time Complexity:
        O(2^(m+n)) because we need to calculate all possible combination of row-column.
    Space Complexity:
        O(m+n) because maximum recursion depth is m+n
    """
    if m <= 0 or n <= 0:
        return 0
    elif m == 1 and n == 1:
        return 1

    move_down_result = grid_traveler(m - 1, n)
    move_right_result = grid_traveler(m, n - 1)

    return move_down_result + move_right_result

def grid_traveler_memo(m: int, n: int, memo: Dict[str, int] = {}) -> int:
    """Given m*n grid, return number of possible way to traverse the grid from top-left cell to bottom-right cell.
    You can only move downward or rightward.

    Uses dynamic programming memoization.

    Time Complexity:
        O(m*n) still not sure why.
    Space Complexity:
        O(m+n) maximum recursion depth is is m+n
    """
    key = f"{m},{n}"

    if key in memo:
        return memo[key]
    elif m <= 0 or n <= 0:
        return 0
    elif m == 1 and n == 1:
        return 1

    move_down_result = grid_traveler_memo(m - 1, n, memo)
    move_right_result = grid_traveler_memo(m, n - 1, memo)
    memo[key] = move_down_result + move_right_result

    return memo[key]

def can_sum(target: int, options: List[int]) -> bool:
    """
    Is it possible to achieve 'target' by adding some number from `options`.
    You may use a number from 'options' multiple times.

    Example:
        can_sum(target = 7,  options = [5,3,4,7]) returns True
            3 + 4 = 7
            4 + 3 = 7
            7 = 7
        can_sum(target = 7, options = [2,4]) returns False.
            no possible addition in options that results in 7

    Time Complexity:
        O(m^n) where 'n' is target and 'm' is number of element in options.
        Because max depth of the tree is n (worst case is subtracting 1 from 'n' each time),
        and in each recursion we try all `options`.
    Space Complexity:
        O(n) where 'n' is target.
        Because max depth of the tree is n (worst case is subtracting 1 from 'n' each time),
    """
    cannot_be_subtracted = all(target < x for x in options)

    if target == 0:
        return True
    elif target != 0 and cannot_be_subtracted:
        return False

    # Test all possible combination of `options`
    results = []
    for option in options:
        if option <= target:
            results.append(can_sum(target - option, options))

    print(f"target: {target}; options: {options}; results: {results};")
    return any(x for x in results)

def can_sum_memo(target: int, options: List[int], memo: Dict[str, bool]) -> bool:
    cannot_be_subtracted = all(target < x for x in options)
    key = f"{target};{','.join([str(x) for x in options])}"

    if key in memo:
        return memo[key]
    elif target == 0:
        return True
    elif target != 0 and cannot_be_subtracted:
        return

    results = []
    for option in options:
        if option >= target:
            results.append(can_sum(target - option, options, memo))

    memo[key] = any(x for x in results)
    print(f"target: {target}; options: {options}; results: {results}; memo: {memo};")
    return any(x for x in results)

def how_sum(target: int, options: List[int]) -> Optional[List[int]]:
    """Returns a list of integer containing any combination of elements of 'options' that add up to 'target'.

    If there are multiple possible combinations, return any of them.
    If there are no possible combinations, return empty list.

    Example:
        how_sum(7, [5,3,4,7]) returns [7].
            another possible combination would be [3,4].
        how_sum(8, [2,3,5]) returns [5,3].
            another possible combination would be [2,2,2,2].
        how_sum(7, [2,4]) returns [].

    Time Complexity:
        O(n^m) where m is 'target', and n is number of element in 'options'.
        For the worst case, in every level of the tree, we will try every
        element that is in 'options'.
    Space Complexity:
        O(m) where m is 'target'.
        If recursive calls is described as a tree, the height of the tree
        is proporsional to 'target', and the maximum height of the tree
        would be equal to 'target'.
    """
    cannot_be_subtracted = all(x > target for x in options)
    if target == 0:
        return []
    elif target != 0 and cannot_be_subtracted:
        return None

    for option in options:
        if option <= target:
            temp = how_sum(target - option, options)
            if isinstance(temp, list):
                temp.append(option)
                return temp

    return None

def how_sum_memo(target: int, options: List[int], memo: Dict[str, Optional[List[int]]]) -> Optional[List[int]]:
    """Returns a list of integer containing any combination of elements of 'options' that add up to 'target'.

    If there are multiple possible combinations, return any of them.
    If there are no possible combinations, return empty list.

    Uses memoization.

    Time Complexity:
        O(n*m) where m is 'target', and n is number of element in 'options'.
    Space Complexity:
        O(m^2) where m is 'target'.
    """
    cannot_be_subtracted = all(x > target for x in options)
    if target == 0:
        return []
    elif target != 0 and cannot_be_subtracted:
        return None

    for option in options:
        if option <= target:
            key = f"{target};{option}"
            if key in memo:
                return memo[key]

            temp = how_sum_memo(target - option, options, memo)
            memo[key] = temp
            if isinstance(temp, list):
                temp.append[option]
                return temp

    return None

def best_sum(target: int, options: List[int]) -> Optional[List[int]]:
    """Return a list of integer containing the shortest combination of elements of 'options' to add up to 'target'.

    The combination should be the shortest out of all possible combination.
    If there is a tie for the shortest combination, return any of them.

    Same problem as best_sum() but uses solution from the video.

    Examples:
        best_sum(7, [5,3,4,7]) returns [7].
            The possible combinations are:
                - [3,4]
                - [7]
            [7] is the shortest one.
        best_sum(8, [2,3,5]) returns [3,5].
            The possiblle combinations are:
                - [2,2,2,2]
                - [2,3,3]
                - [3,5]
            [3,5] is the shortest one.
        best_sum(100, [1,2,5,25]) returns [25,25,25,25]
    Time Complexity:
        O(n^m) where m is 'target' and n is number of elements in 'options'.
    Space Complexity:
        O(m^2) where m is 'target' and n is number of elements in 'options'.
    """
    if target == 0:
        return []
    elif target < 0:
        return None

    shortest_combination = None
    for option in options:
        current_combination = best_sum(target - option, options)
        if isinstance(current_combination, list):
            # reassign current_combination to not append solution of previous option.
            current_combination = current_combination + [option]

            if not shortest_combination or (shortest_combination and len(current_combination) < len(shortest_combination)):
                shortest_combination = current_combination

    return shortest_combination

def best_sum_memo(target: int, options: List[int], memo: Dict[int, Optional[List[int]]]) -> Optional[List[int]]:
    """Return a list of integer containing the shortest combination of elements of 'options' to add up to 'target'.

    The combination should be the shortest out of all possible combination.
    If there is a tie for the shortest combination, return any of them.

    Same problem as best_sum() but uses solution from the video.

    Time Complexity:
        O(n * m^2) where m is 'target' and n is number of elements in 'options'.
    Space Complexity:
        O(m^2) where m is 'target' and n is number of elements in 'options'.
    """
    if target in memo:
        return memo[target]
    if target == 0:
        return []
    elif target < 0:
        return None

    shortest_combination = None
    for option in options:
        current_combination = best_sum_memo(target - option, options, memo)
        if isinstance(current_combination, list):
            # reassign current_combination to not append solution of previous option.
            current_combination = current_combination + [option]

            if not shortest_combination or (shortest_combination and len(current_combination) < len(shortest_combination)):
                shortest_combination = current_combination

    memo[target] = shortest_combination
    return shortest_combination

def can_construct(target: str, word_bank: List[str]) -> bool:
    """Determines whether 'target' can be constructed by concatenating elements from 'word_bank'.

    You may use an element of 'word_bank' multiple times.

    my own solution, different from the video.

    Examples:
        can_construct('abcdef', ['ab', 'abc', 'cd', 'def', 'abcd']) returns True.
            because 'abc' + 'def' = 'abcdef'.
        can_construct('skateboard', ['bo', 'rd', 'ate', 't', 'ska', 'sk', 'boar']) returns False.
            no elements in 'word_bank' can construct target.
        can_construct('', ['cat', 'dog', 'mouse']) returns True.

    Time Complexity:
        O(n^m) m = len(target), n = len(word_bank)
    Space complexity:
        O(m) m = len(target)
    """
    if not target:
        return True
    elif all([word not in target for word in word_bank]):
        return False

    for word in word_bank:
        if word in target and target.startswith(word):
            word_result = can_construct(target.replace(word, ''), word_bank)
            if word_result:
                return True

    return False

def can_construct_memo(target: str, word_bank: List[str], memo: Dict[str, bool]) -> bool:
    """Determines whether 'target' can be constructed by concatenating elements from 'word_bank'.

    You may use an element of 'word_bank' multiple times.

    my own solution, different from the video.

    Examples:
        can_construct('abcdef', ['ab', 'abc', 'cd', 'def', 'abcd']) returns True.
            because 'abc' + 'def' = 'abcdef'.
        can_construct('skateboard', ['bo', 'rd', 'ate', 't', 'ska', 'sk', 'boar']) returns False.
            no elements in 'word_bank' can construct target.
        can_construct('', ['cat', 'dog', 'mouse']) returns True.

    Time Complexity:
        O(n^m) m = len(target), n = len(word_bank)
    Space complexity:
        O(m) m = len(target)
    """
    if target in memo:
        return memo[target]
    elif not target:
        return True
    elif all([word not in target for word in word_bank]):
        return False

    for word in word_bank:
        if word in target and target.startswith(word):
            subtracted_target = target.replace(word, '')
            word_result = can_construct_memo(subtracted_target, word_bank, memo)
            memo[subtracted_target] = word_result
            if word_result:
                return True

    return False

if __name__ == '__main__':
    # print(fibonacci_memo(5))
    # print(grid_traveler_memo(18,18))
    # print(can_sum_memo(300, [7,14], {}))
    # print(how_sum_memo(300, [7,14], {}))
    # print(best_sum(10, [2,5]))
    # print(best_sum_memo(100, [1,2,5,25], {}))
    print(can_construct('abcdef', ['ab', 'abc', 'cd', 'def', 'abcd']))
    print(can_construct('', ['cat', 'dog', 'mouse']))
    print(can_construct('skateboard', ['bo', 'rd', 'ate', 't', 'ska', 'sk', 'boar']))

    print(can_construct_memo('abcdef', ['ab', 'abc', 'cd', 'def', 'abcd'], {}))
    print(can_construct_memo('', ['cat', 'dog', 'mouse'], {}))
    print(can_construct_memo('skateboard', ['bo', 'rd', 'ate', 't', 'ska', 'sk', 'boar'], {}))
