<template>
    <div class="calculator">
        <div class="display">{{ display }}</div>
        <div class="buttons">
            <div class="row">
                <button @click="handleClick('7')">7</button>
                <button @click="handleClick('8')">8</button>
                <button @click="handleClick('9')">9</button>
                <button class="operator" @click="handleClick('/')">÷</button>
            </div>
            <div class="row">
                <button @click="handleClick('4')">4</button>
                <button @click="handleClick('5')">5</button>
                <button @click="handleClick('6')">6</button>
                <button class="operator" @click="handleClick('*')">x</button>
            </div>
            <div class="row">
                <button @click="handleClick('1')">1</button>
                <button @click="handleClick('2')">2</button>
                <button @click="handleClick('3')">3</button>
                <button class="operator" @click="handleClick('-')">-</button>
            </div>
            <div class="row">
                <button @click="handleClick('0')">0</button>
                <button class="decimal" @click="handleClick('.')">.</button>
                <button class="operator" @click="handleClick('=')">=</button>
                <button class="operator" @click="handleClick('+')">+</button>
            </div>
            <div class="row">
                <button class="clear" @click="handleClear">C</button>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { ref } from 'vue';

export default {
    setup() {
        const display = ref('0');
        const operator = ref('');
        const operand1 = ref(0);
        const operand2 = ref(0);
        const result = ref(0);

        const handleClick = (value: string) => {
            if (value === '+' || value === '-' || value === '*' || value === '/') {
                operator.value = value;
                operand1.value = parseFloat(display.value);
                display.value = '0';
            } else if (value === '=') {
                operand2.value = parseFloat(display.value);
                switch (operator.value) {
                    case '+':
                        result.value = operand1.value + operand2.value;
                        break;
                    case '-':
                        result.value = operand1.value - operand2.value;
                        break;
                    case '*':
                        result.value = operand1.value * operand2.value;
                        break;
                    case '/':
                        result.value = operand1.value / operand2.value;
                        break;
                }
                display.value = result.value.toString();
                operator.value = '';
                operand1.value = 0;
                operand2.value = 0;
                result.value = 0;
            } else if (value === '.') {
                if (!display.value.includes('.')) {
                    display.value += '.';
                }
            } else if (value === 'C') {
                display.value = '0';
                operator.value = '';
                operand1.value = 0;
                operand2.value = 0;
                result.value = 0;
            } else {
                if (display.value === '0') {
                    display.value = value;
                } else {
                    display.value += value;
                }
            }
        };

        const handleClear = () => {
            display.value = '0';
            operator.value = '';
            operand1.value = 0;
            operand2.value = 0;
            result.value = 0;
        };

        return {
            display,
            handleClick,
            handleClear,
        };
    },
};
</script>

<style scoped>
.calculator {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
    background-color: #f5f5f5;
}

.display {
    display: flex;
    align-items: center;
    justify-content: flex-end;
    width: 300px;
    height: 50px;
    background-color: #fff;
    border: 1px solid #ccc;
    padding: 0 10px;
    font-size: 24px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    border-radius: 5px;
    margin-bottom: 20px;
}

.buttons {
    display: flex;
    flex-direction: column;
    margin-top: 20px;
}

.row {
    display: flex;
    justify-content: space-between;
    margin-bottom: 10px;
}

button {
    width: 70px;
    height: 70px;
    font-size: 24px;
    background-color: #fff;
    border: 1px solid #ccc;
    border-radius: 5px;
    cursor: pointer;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    transition: all 0.2s ease-in-out;
}

button:hover {
    background-color: #ccc;
}

.operator {
    background-color: #f44336;
    color: #fff;
}

.operator:hover {
    background-color: #e53935;
}

.decimal {
    background-color: #4caf50;
    color: #fff;
}

.decimal:hover {
    background-color: #388e3c;
}

.clear {
    background-color: #9e9e9e;
    color: #fff;
}

.clear:hover {
    background-color: #757575;
}
</style>
