#include <iostream>
#include <random>
#include <string>

using namespace std;

// Генерация числа, не имеющего повторяющихся цифр
// size - размер загадываемого числа
string generateNumber(unsigned short size);

// Проверка числа на соответствие загаданному
// input - ввод пользователя
// example - загаданное компьютером число
// Метод возвращает тип данных pair<T, K>, то есть пару двух элементов указанных типов T и K соответственно
pair<unsigned short, unsigned short> checkNumber(string input, string example);

// Включить проверку переменных
bool debug = false;

int main() {
    // Установка сида для рандомной генерации числа в зависимости от текущего времени.
    // Это нужно для того, чтобы при каждом запуске программы загадываемые числа не повторялись
    srand((unsigned)time(NULL)); 
    cout << "Игра \"Быки и коровы\": Компьютер задумывает четырёхзначное число.\n";
    cout << "Пользователь пытается угадать это число, а компьютер выводит количество цифр, точно стоящих\n";
    cout << "на своих местах (+), и количество цифр, которые есть в числе, но расположены на другой позиции (-)\n";
    
    string command; // Сюда записываем число, которое вводит пользователь

    // Размер загадываемого числа
    unsigned short numberSize = 4;

    // Сгенерированное число
    string currentNumber = "";

    while (true)
    {
        if (currentNumber == "") currentNumber = generateNumber(4);
        getline(cin, command); // Ввод числа
        // Погружаем основной код в конструкцию try-catch. Если произойдет какая-либо ошибка при проверке ввода,
        // то блок catch перехватит ее и выведет в консоль текст ошибки
        try
        {
            auto x = checkNumber(command, currentNumber); // Проверка числа на соответствие загаданному
            cout << x.first << " плюсов\n"; // first - первый элемент пары
            cout << x.second << " минусов\n"; // second - второй элемент пары
            // Проверяем, что число плюсов равно размеру загаданного числа (это означает, что пользователь угадал верно)
            if (x.first == currentNumber.size()) { 
                currentNumber = ""; // Очищаем переменную, хранящую загаданное число
                cout << "Число разгадано!\n";
            }
        }
        // Блок catch, в данном случае он отлавливает исключение invalid_argument (то есть, неверный аргумент)
        // const означает, что мы не собираемся модифицировать полученное исключение, а только читать его содержимое
        // & означает, что мы принимаем ссылку на исключение, если принимать не по ссылке, может быть утеряна какая-либо
        // важная информация, так как будет вызываться конструктор копирования
        catch(const invalid_argument& e)
        {
            // cerr в этом контексте то же самое, что и cout, просто используется для вывода ошибок
            cerr << e.what() << '\n'; // Выводим текст исключения (что вызвало ошибку при проверке числа на соответствие)
        }
        
    }
}

string generateNumber(unsigned short size)
{
    // Массив из 10 элементов (каждый соответствует своей цифре)
    // Как только к загадываемому числу добавляется очередная цифра, мы отмечаем
    // в массиве, что такая цифра уже есть, чтобы потом не повторяться
    bool used[10] {false};
    // Переменная, в которую последовательно записываем цифры загадываемого числа
    string newNumber = "";
    // Цикл, в котором генерируем число
    for (size_t i = 0; i < size; i++) { 
        // Генерируем случайную цифру от 0 до 9
        auto gen = rand() % size;
        // Проверяем, что такая цифра не использовалась, тогда дописываем ее в число
        // Иначе просто уменьшаем счетчик цикла i на 1 и продолжаем цикл
        if (used[gen]) i--;
        else newNumber += to_string(gen); 
        // Отмечам использованно число
        used[gen] = true;
    }
    // Вывод промежуточных значений для проверки работы программы
    if (debug) {
        cout << "Сгенерированное число: " << newNumber << endl;
        cout << "Размер: " << newNumber.size() << endl; }
    return newNumber;
}

pair<unsigned short, unsigned short> checkNumber(string input, string example)
{
    // Вывод промежуточных значений для проверки работы программы
    if (debug) cout << "Input size: " << input.size() << endl;
    // Проверяем, что введенная строка равна по размеру загаданному числу, иначе выбрасываем исключение
    // invalid_argument (неверный аргумент) с указанным текстом ошибки
    if (input.size() != example.size()) throw invalid_argument("Размер ввода не соответствует размеру загаданного числа");
    
    unsigned short pluses = 0; // Переменная для хранения плюсов
    unsigned short minuses = 0; // Переменная для хранения минусов

    // Цикл для проверки каждой цифры ввода с загаданным числом
    for (size_t i = 0; i < example.size(); i++)
        // Проверка, что символ является цифрой, иначе выбрасываем исключение invalid_argument (неверный аргумент)
        if (!isdigit(input[i])) throw std::invalid_argument("Введенная строка не является числом");
        // Проверка, что цифра ввода на позиции i равна цифре загаданного числа на позиции i
        else if (input[i] == example[i]) pluses++;
        // Проверка, что цифра ввода на позиции i вообще присутствует в загаданном числе (если предыдущаяя проверка была
        // неудачной). Для этого применяется метод find. Этот метод ищет переданную в качестве аргумента подстроку и либо
        // возвращает индекс этой подстроки, если нашел ее, либо string::npos, если не сумел найти.
        // string::npos -  это измеримое значение, эквивалентное наибольшему значению, которое может описать тип size_t
        // size_t - это unsigned int (для 32-битного компилятора) или unsigned long long (для 64-битного компилятора)
        else if (example.find(input[i]) != string::npos) minuses++;
    // Создаем пару, состоящую из количества плюсов и количества минусов, возвращаем ее в основную программу
    return make_pair(pluses, minuses);
}