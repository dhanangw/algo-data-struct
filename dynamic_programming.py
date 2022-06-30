"""From https://www.youtube.com/watch?v=oBt53YbR9Kk.

Memoization guide:
1. Draw the recursion tree
2. Implement the brute force solution ('Make it work')
3. Apply memoization to it ('Make it fast')

Tabulation guide:
1. Visualise the problem as a table.
2. Size the table according to input (add 1 to table size to not get "off by 1" error).
2.a. each cell in the table is a sub-problem of the original problem.
3. Initialise the table with default values. data type of default values follows data type of function's return value.
3.a. Value of table is what you're trying to optimise.
4. Seed the trivial answer to the table
4.a trivial answers == base cases.
5. Iterate through the table.
6. Fill further positions based on the current position.
"""
from typing import Dict, List, Optional, Tuple


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

def fibonacci_tab(n: int) -> int:
    """Returns n-th fibonacci number.

    Uses dynamic programming tabulation.

    Time Complexity:
        O(n)
    Space Complexity:
        O(n)
    """
    # create table
    table = [0] * (n + 1)

    # apply base case to table
    table[0] = 0
    table[1] = 1

    # iterate through table
    for index, value in  enumerate(table):
        try:
            table[index + 1] += value
        except IndexError:
            pass

        try:
            table[index + 2] += value
        except IndexError:
            pass

    return table[n]


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

def grid_traveler_tab(m: int, n: int) -> int:
    """Given m*n grid, return number of possible way to traverse the grid from top-left cell to bottom-right cell.
    You can only move downward or rightward.

    Uses dynamic programming tabulation.

    Time Complexity:
        O(m*n)
    Space Complexity:
        O(m*n)
    """
    # initiate table (for 2D array, use https://stackoverflow.com/a/44382900)
    table = [[0] * (n + 1) for i in range(m + 1)]

    # apply base case to table
    table[1][1] = 1

    # iterate through table 
    for row_index, row in enumerate(table):
        for column_index, cell_value in enumerate(row):
            if column_index + 1 <= n:
                table[row_index][column_index + 1] += cell_value

            if row_index + 1 <= m:
                table[row_index + 1][column_index] += cell_value

    return table[m][n]


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

def can_sum_tab(target: int, options: List[int]) -> bool:
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

    Uses dynamic programming tabulation

    Time Complexity:
        O(m^n) where m is `target` and n is len(`options`)
    Space Complexity:
        O(m) where m is `target`
    """
    # initialise table
    table = [False] * (target + 1)

    # apply base cases to table
    table[0] = True

    # iterate through table
    for index, value in enumerate(table):
        for option in options:
            look_ahead_index = index + option
            if value and look_ahead_index <= target:
                table[index + option] = True

    return table[target]

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
        how_sum(300, [7,14]) returns [].

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
                temp.append(option)
                return temp

    return None

def how_sum_tab(target: int, options: List[int]) -> Optional[List[int]]:
    """Returns a list of integer containing any combination of elements of 'options' that add up to 'target'.

    If there are multiple possible combinations, return any of them.
    If there are no possible combinations, return empty list.

    Uses tabulation.

    Time Complexity:
        O(m * n) where m = target and n= len(options)
    Space Complexity:
        O(m^2) where m is target
    """
    table = [None] * (target + 1)

    table[0] = []

    for index, value in enumerate(table):
        if isinstance(value, list):
            for option in options:
                next_index = index + option
                if next_index <= target:
                    table[next_index] = value + [option]

    return table[target]


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

def best_sum_tab(target: int, options: List[int]) -> Optional[List[int]]:
    """Return a list of integer containing the shortest combination of elements of 'options' to add up to 'target'.

    The combination should be the shortest out of all possible combination.
    If there is a tie for the shortest combination, return any of them.

    Uses dynamic programming tabulation

    Time Complexity:
        O(m*n) where m is 'target' and n is 'len(options)'
    Space Complexity:
        O(m^2) where m is 'target'
    """
    table_size = target + 1
    table = [None] * table_size

    table[0] = []

    for index, value in enumerate(table):
        if isinstance(value, list):
            for option in options:
                next_index = index + option
                next_value = value + [option]

                if next_index <= target and (not table[next_index] or len(table[next_index]) > len(next_value)):
                    table[next_index] = next_value

    return table[target]

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
        O(n*m) m = len(target), n = len(word_bank)
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

    memo[target] = False
    return False

def can_construct_tab(target: str, word_bank: List[str]) -> bool:
    """Determines whether 'target' can be constructed by concatenating elements from 'word_bank'.

    You may use an element of 'word_bank' multiple times.

    Uses dynamic programming tabulation.

    Examples:
        can_construct('abcdef', ['ab', 'abc', 'cd', 'def', 'abcd']) returns True.
            because 'abc' + 'def' = 'abcdef'.
        can_construct('skateboard', ['bo', 'rd', 'ate', 't', 'ska', 'sk', 'boar']) returns False.
            no elements in 'word_bank' can construct target.
        can_construct('', ['cat', 'dog', 'mouse']) returns True.

    Time Complexity:
        O(m*n) where m is len(target) and n is len(word_bank)
    Space complexity:
        O(m) where m is len(target)
    """
    table = [False] * (len(target) + 1)

    table[0] = True

    for index, value in enumerate(table):
        if value:
            for word in word_bank:
                if target[index:].startswith(word):
                    table[index + len(word)] = True

    return table[len(target)]

def count_construct(target: str, word_bank: List[str]) -> int:
    """Return number of ways 'target' can be constructed by concatenating elements of 'word_bank'.

    You may use an element of 'word_bank' multiple times.

    Example:
        count_construct('abcdef', ['ab', 'abc', 'cd', 'def', 'abcd']) returns 1.
            because there is only 1 possible combination: 'abc' + 'def' = 'abcdef'.
        count_construct('purple', ['purp', 'p', 'ur', 'le', 'purpl']) returns 2.
        count_construct('skateboard', ['bo', 'rd', 'ate', 't', 'ska', 'sk', 'boar']) returns 0.
        count_construct('enterapotentpot', ['a', 'p', 'ent', 'enter', 'ot', 'o', 't']) returns 4.
        count_construct('eeeeeeeeeeeeeeeeeeeeeeef', ['e', 'ee', 'eee', 'eeee', 'eeeee', 'eeeeee']) returns 0.

    Time complexity:
        O(n^m) n = len(word_bank) m = len(target)
    Space complexity:
        O(m) m = len(target)
    """
    if not target:
        return 1
    elif target and all([not target.startswith(word) for word in word_bank]):
        return 0

    results = 0
    for word in word_bank:
        if word in target and target.startswith(word):
            results += count_construct(target.replace(word, '', 1), word_bank)

    return results

def count_construct_memo(target: str, word_bank: List[str], memo: Dict[str, int]) -> int:
    """Return number of ways 'target' can be constructed by concatenating elements of 'word_bank'.

    You may use an element of 'word_bank' multiple times.

    Example:
        count_construct('abcdef', ['ab', 'abc', 'cd', 'def', 'abcd']) returns 1.
            There is only 1 possible combination: 'abc' + 'def' = 'abcdef'.
        count_construct('purple', ['purp', 'p', 'ur', 'le', 'purpl']) returns 2.
            There are 2 combinations:
                - purp + le
                - p + ur + p + le
        count_construct('skateboard', ['bo', 'rd', 'ate', 't', 'ska', 'sk', 'boar']) returns 0.
        count_construct('enterapotentpot', ['a', 'p', 'ent', 'enter', 'ot', 'o', 't']) returns 4.
        count_construct('eeeeeeeeeeeeeeeeeeeeeeef', ['e', 'ee', 'eee', 'eeee', 'eeeee', 'eeeeee']) returns 0.

    Time complexity:
        O(n*m) n = len(word_bank) m = len(target)
    Space complexity:
        O(m) m = len(target)
    """
    if target in memo:
        return memo[target]
    elif not target:
        return 1
    elif target and all([not target.startswith(word) for word in word_bank]):
        return 0

    results = 0
    for word in word_bank:
        if target.startswith(word):
            results += count_construct_memo(target.replace(word, '', 1), word_bank, memo)

    memo[target] = results
    return results

def all_construct(target: str, word_bank: List[str]) -> Optional[List[List[str]]]:
    """Return a 2D array containing all of the ways that 'target' can be constructed by concatenating elements of 'word_bank'.

    Each element of the 2D array represent one possible combination.
    You may use element of 'word_bank' multiple times.

    Example:
        all_construct('purple', ['purp', 'p', 'ur', 'le', 'purpl']) returns
        [
            ['purpl', 'e'],
            ['p', 'ur', 'p', 'le']
        ]
        all_construct('abcdef', ['ab', 'abc', 'cd', 'def', 'abcd', 'ef', 'c']) returns
        [
            ['ab', 'cd', 'ef'],
            ['ab', 'c', 'def'],
            ['abc', 'def'],
            ['abcd', 'ef'],
        ]
        all_construct('hello', ['cat', 'dog', 'mouse']) returns []
        all_construct('', ['cat', 'dog', 'mouse']) returns [[]]
            because there is one way: concatenate nothing from word_bank.
    """
    if not target:
        return [[]]
    elif target and all([not target.startswith(word) for word in word_bank]):
        return None

    total_result = []
    for word in word_bank:
        if target.startswith(word):
            subtracted_word = target.replace(word, '', 1)
            word_results = all_construct(subtracted_word, word_bank)
            if isinstance(word_results, list):
                # prepend word in each word_result
                for word_result in word_results:
                    word_result.insert(0, word)
                total_result += word_results

    return total_result

def all_construct_memo(target: str, word_bank: List[str], memo: Dict[str, Optional[List[List[str]]]]) -> Optional[List[List[str]]]:
    """Return a 2D array containing all of the ways that 'target' can be constructed by concatenating elements of 'word_bank'.

    Each element of the 2D array represent one possible combination.
    You may use element of 'word_bank' multiple times.

    Optimised with memoisation.

    Example:
        all_construct('purple', ['purp', 'p', 'ur', 'le', 'purpl']) returns
        [
            ['purpl', 'e'],
            ['p', 'ur', 'p', 'le']
        ]
        all_construct('abcdef', ['ab', 'abc', 'cd', 'def', 'abcd', 'ef', 'c']) returns
        [
            ['ab', 'cd', 'ef'],
            ['ab', 'c', 'def'],
            ['abc', 'def'],
            ['abcd', 'ef'],
        ]
        all_construct('hello', ['cat', 'dog', 'mouse']) returns []
        all_construct('', ['cat', 'dog', 'mouse']) returns [[]]
            because there is one way: concatenate nothing from word_bank.
    """
    if target in memo:
        return memo[target]
    elif not target:
        return [[]]
    elif target and all([not target.startswith(word) for word in word_bank]):
        return None

    total_result = []
    for word in word_bank:
        if target.startswith(word):
            subtracted_word = target.replace(word, '', 1)
            word_results = all_construct(subtracted_word, word_bank)
            if isinstance(word_results, list):
                # prepend word in each word_result
                for word_result in word_results:
                    word_result.insert(0, word)
                total_result += word_results

    memo[target] = total_result
    return total_result

if __name__ == '__main__':
    # print(fibonacci_memo(5))
    # print(fibonacci_tab(5))

    # print(grid_traveler_memo(18,18))
    # print(grid_traveler_tab(18,18))

    # print(can_sum_memo(300, [7,14], {}))
    # print(can_sum_tab(300, [7,14]))

    # print(how_sum_memo(300, [7,14], {}))
    # print(how_sum_tab(300, [7,14]))

    # print(best_sum_memo(100, [1,2,5,25], {}))
    # print(best_sum_tab(100, [1,2,5,25]))

    # print(can_construct('skateboard', ['bo', 'rd', 'ate', 't', 'ska', 'sk', 'boar']))
    print(can_construct_memo('skateboard', ['bo', 'rd', 'ate', 't', 'ska', 'sk', 'boar'], {}))
    print(can_construct_tab('skateboard', ['bo', 'rd', 'ate', 't', 'ska', 'sk', 'boar']))

    # print(count_construct('eeeeeeeeeeeeeeeeeeeeeeef', ['e', 'ee', 'eee', 'eeee', 'eeeee', 'eeeeee']))
    # print(count_construct_memo('eeeeeeeeeeeeeeeeeeeeeeef', ['e', 'ee', 'eee', 'eeee', 'eeeee', 'eeeeee'], {}))
    # print(all_construct('purple', ['purp', 'p', 'ur', 'le', 'purpl']))
    # print(all_construct('', ['cat', 'dog', 'mouse']))
    # print(all_construct_memo('purple', ['purp', 'p', 'ur', 'le', 'purpl'], {}))
    # print(all_construct_memo('', ['cat', 'dog', 'mouse'], {}))

