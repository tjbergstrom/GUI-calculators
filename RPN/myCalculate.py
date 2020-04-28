# push operands onto stack
# calculate the expression

import myStack


# make some operators to use in calculation
operators = {
    "+": (lambda a, b: a + b),
    "-": (lambda a, b: a - b),
    "*": (lambda a, b: a * b),
    "/": (lambda a, b: a / b),
    "^": (lambda a, b: a ** b),
    "%": (lambda a, b: a % b),
    "rt(y)": (lambda a: a ** .5)
}


# perform calculations
def calculate(args):
    # make a Stack
    stackObject = myStack.Stack()
    # find operators
    for arg in args:
        # pop operands from stack when operator is found
        if arg in operators:
            # unary operators
            if arg == 'sqrt':
                # check underflow error, next in stack should be a number
                try:
                    operand = stackObject.pop()
                    answer = operators[arg](operand)
                    # push the answer back onto the stack to keep evaluating
                    # with this answer as the next possible operand
                    stackObject.push(answer)
                except IndexError:
                    print("Stack underflow error")
                    return "DNE"
            # binary operators
            else:
                # check underflow error
                try:
                    # second operand should be on top of stack
                    secondOperand = stackObject.pop()
                    firstOperand = stackObject.pop()
                    # /0 error
                    if arg == '/' and secondOperand == 0:
                        print("Divide by zero error")
                        return "DNE"
                    else:
                        # use operators to evaluate
                        answer = operators[arg](firstOperand, secondOperand)
                        # push answer back onto stack
                        stackObject.push(answer)
                except IndexError:
                    # if firstOperand not available
                    print("Stack underflow error")
                    return "DNE"
        # check for syntax error
        else:
            try:
                # push operands to stack
                # (if number then convert string to float and push)
                stackObject.push(float(arg))
            except ValueError:
                # if not a number then it's a syntax error and doesn't push
                print("Syntax Error")
                return "DNE"
    # if no more operators found then pop final answer
    finalAnswer = stackObject.pop()
    # check if stack still contains more values for too many operands error
    if stackObject.size() > 0:
        print("Too many operands error")
        return "DNE"
    else:
        # and then return the popped final answer
        return finalAnswer


class RPNcalc:
    def rpn(exp):
        try:
            answer = myCalculate.calculate(exp.split())
        except ValueError:
            e = sys.exc_info()[0]
            print(e)
        return str(answer)



