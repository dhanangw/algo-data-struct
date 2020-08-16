class LinkedListStack:
    class Node:
        def __init__(self, value: int) -> None:
            self.value = value
            self.next = None
    
    top = None

    def push(self, value: int) -> None:
        new_node = self.Node(value)
        new_node.next = self.top
        self.top = new_node
    
    def pop(self) -> Node:
        node = self.top
        if node and self.top:
            self.top = self.top.next
            return node
        else:
            raise ValueError('stack is empty')
    
    def is_empty(self) -> bool:
        return self.top == None
    
    def print_stack(self) -> None:
        pointer = self.top
        while(pointer != None):
            print(pointer.value)
            pointer = pointer.next

class PostfixPrefixEvaluator():
    def __init__(self):
        self.stack = LinkedListStack()
    
    def operate(self, operand1: int, operand2: int, operator: str) -> int:
        result = None
        if operator == '+':
            return operand1 + operand2
        if operator == '-':
            return operand1 - operand2
        if operator == '*':
            return operand1 * operand2
        if operator == '/':
            return int(operand1 / operand2)

    
    def is_postfix_valid(self, postfix_expression: str) -> bool:
        # loop through expression left to right
        # if operand, push to stack
        # else if operator, get 2 recent operand from stack and operate with operator
        # if expression is looped, and stack is empty then expression is valid. else expression is unvalid.
        try:
            for i in range(0, len(postfix_expression)):
                char = postfix_expression[i]
                if char not in {'+', '-', '*', '/'}:
                    self.stack.push(int(char))
                elif char in {'+', '-', '*', '/'}:
                    operand1 = self.stack.pop().value
                    operand2 = self.stack.pop().value
                    result = self.operate(operand1, operand2, char)
                    self.stack.push(result)
            return True
        except ValueError as e:
            if str(e) == 'stack is empty':
                return False
    
    def is_prefix_valid(self, prefix_expression: str) -> bool:
        # loop through expression right to left
        # if operand, push to stack
        # else if operator, get 2 recent operand from stack and operate with operator
        # if expression is looped, and stack is empty then expression is valid. else expression is unvalid.
        try:
            for i in range(len(prefix_expression) - 1, -1, -1):
                char = prefix_expression[i]
                if char not in {'+', '-', '*', '/'}:
                    self.stack.push(int(char))
                elif char in {'+', '-', '*', '/'}:
                    operand1 = self.stack.pop().value
                    operand2 = self.stack.pop().value
                    result = self.operate(operand1, operand2, char)
                    self.stack.push(result)
            return True
        except ValueError as e:
            if str(e) == 'stack is empty':
                return False
        

if __name__ == '__main__':
    # infix_expression = "(2+3)-4/2"
    postfix_expression = "23+42/-"
    prefix_expression = "-+23*/42"
    evaluator = PostfixPrefixEvaluator()
    print(evaluator.is_postfix_valid(postfix_expression))
    print(evaluator.is_prefix_valid(prefix_expression))
    





