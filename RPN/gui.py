# a GUI for an RPN calculator


import sys
from PyQt5.QtWidgets import QApplication
from PyQt5.QtWidgets import QMainWindow
from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QWidget
from PyQt5.QtCore import Qt
from PyQt5.QtWidgets import QGridLayout
from PyQt5.QtWidgets import QLineEdit
from PyQt5.QtWidgets import QPushButton
from PyQt5.QtWidgets import QVBoxLayout
from functools import partial
from RPN import RPNcalc
import qdarkstyle
import PyQt5.QtWidgets


def evalu(exp):
    try:
        result = RPNcalc.rpn(exp)
    except Exception:
        result = "ERROR"
    return exp + " = " + result + " "


class Input:
    def __init__(self, model, view):
        self._evaluate = model
        self._view = view
        self._connectSignals()

    def _calculateResult(self):
        result = self._evaluate(exp=self._view.displayText())
        self._view.setDisplayText(result)

    def _buildExpression(self, sub_exp):
        if self._view.displayText() == "ERROR":
            self._view.clearDisplay()
        exp = self._view.displayText() + sub_exp
        self._view.setDisplayText(exp)

    def _connectSignals(self):
        for btnText, btn in self._view.buttons.items():
            if btnText not in {'=', 'C'}:
                if btnText == '\" \"':
                    btnText = " "
                btn.clicked.connect(partial(self._buildExpression, btnText))
        self._view.buttons['='].clicked.connect(self._calculateResult)
        self._view.display.returnPressed.connect(self._calculateResult)
        self._view.buttons['C'].clicked.connect(self._view.clearDisplay)


class Gui(QMainWindow):
    def setDisplayText(self, text):
        self.display.setText(text)
        self.display.setFocus()

    def displayText(self):
        return self.display.text()

    def clearDisplay(self):
        self.setDisplayText('')

    def _createButtons(self):
        self.buttons = {}
        buttonsLayout = QGridLayout()
        buttons = {'7': (0, 0), '8': (0, 1), '9': (0, 2),
                   '/': (0, 3), 'C': (0, 4), '4': (1, 0),
                   '5': (1, 1), '6': (1, 2), '*': (1, 3),
                   '^': (1, 4), '1': (2, 0), '2': (2, 1),
                   '3': (2, 2), '-': (2, 3), 'rt(y)': (2, 4),
                   '0': (3, 0), '\" \"': (3, 1), '.': (3, 2),
                   '+': (3, 3), '=': (3, 4),
                  }
        for btnText, pos in buttons.items():
            self.buttons[btnText] = QPushButton(btnText)
            self.buttons[btnText].setFixedSize(40, 40)
            buttonsLayout.addWidget(self.buttons[btnText], pos[0], pos[1])
            self.generalLayout.addLayout(buttonsLayout)

    def displayText(self):
        return self.display.text()

    def clearDisplay(self):
        self.setDisplayText('')

    def _createDisplay(self):
        self.display = QLineEdit()
        self.display.setFixedHeight(35)
        self.display.setAlignment(Qt.AlignRight)
        self.display.setReadOnly(True)
        self.generalLayout.addWidget(self.display)

    def __init__(self):
        super().__init__()
        self.setWindowTitle('RPN')
        self.setFixedSize(320, 320)
        self.generalLayout = QVBoxLayout()
        self._centralWidget = QWidget(self)
        self._centralWidget.setLayout(self.generalLayout)
        self.setCentralWidget(self._centralWidget)
        self._createDisplay()
        self._createButtons()



def main():
    calc = QApplication(sys.argv)
    view = Gui()
    calc.setStyle('windows')
    #calc.setStyleSheet(qdarkstyle.load_stylesheet())
    view.show()
    model = evalu
    Input(model=model, view=view)
    sys.exit(calc.exec_())


main()



