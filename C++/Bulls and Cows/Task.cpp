#include <iostream>
#include <sstream>
#include <random>
#include <string>

using namespace std;

// Генерация числа, не имеющего повторяющихся цифр
string generateNumber(unsigned short size);

// Проверка числа на соответствие загаданному
pair<unsigned short, unsigned short> checkNumber(string input, string example);

// Вывод команд
void showHelp();

// Включить проверку переменных
bool debug = false;

int main() {
    srand((unsigned)time(NULL));
    cout << "Игра \"Быки и коровы\": Компьютер задумывает четырёхзначное число.\n";
    cout << "Пользователь пытается угадать это число, а компьютер выводит количество цифр, точно стоящих\n";
    cout << "на своих местах (+), и количество цифр, которые есть в числе, но расположены на другой позиции (-)\n";
    
    showHelp();
    string command;

    // Размер загадываемого числа
    unsigned short numberSize = 4;

    // Сгенерированное число
    string currentNumber = "";

    while (true)
    {
        getline(cin, command);
        string sm[2];
        stringstream ss(command);
        for (size_t i = 0; i < 2 && !ss.eof(); i++) ss >> sm[i];
        
        if (sm[0] == "help") showHelp();
        else if (sm[0] == "size") {
            unsigned short newSize;
            try { 
                newSize = stoi(sm[1]);
                numberSize = newSize;    
            }
            catch(...) { cout << "Ошибка при вводе команды"; }
        }
        else if (sm[0] == "gen") {
            currentNumber = generateNumber(numberSize);
            cout << "А теперь попробуйте угадать это число!\n";
        }
        else if (sm[0] == "quit") return 0;
        else if (currentNumber != "") {
            try
            {
                auto x = checkNumber(sm[0], currentNumber);
                cout << x.first << " плюсов\n";
                cout << x.second << " минусов\n";
                if (x.first == currentNumber.size()) { 
                    currentNumber = "";
                    cout << "Число разгадано!\n";
                }
            }
            catch(const invalid_argument& e)
            {
                std::cerr << e.what() << '\n';
            }
        }
    }
}

string generateNumber(unsigned short size)
{
    bool used[10] {false};
    string newNumber = "";
    for (size_t i = 0; i < size; i++) { 
        auto gen = rand() % size;
        if (used[gen]) i--;
        else newNumber += to_string(gen); 
        used[gen] = true;
    }
    if (debug) {
    cout << "Сгенерированное число: " << newNumber << endl;
    cout << "Размер: " << newNumber.size() << endl; }
    return newNumber;
}

pair<unsigned short, unsigned short> checkNumber(string input, string example)
{
    if (debug) cout << "Input size: " << input.size() << endl;
    if (input.size() != example.size()) throw std::invalid_argument("Размер ввода не соответствует размеру загаданного числа");
    
    unsigned short pluses = 0;
    unsigned short minuses = 0;

    for (size_t i = 0; i < example.size(); i++)
        if (!isdigit(input[i])) throw std::invalid_argument("Введенная строка не является числом");
        else if (input[i] == example[i]) pluses++;
        else if (example.find(input[i]) != string::npos) minuses++;
    return make_pair(pluses, minuses);
}

void showHelp()
{
    cout << "size <число> - изменить размер загадываемого числа (от 1 до 10)\n";
    cout << "gen - сгенерировать новое число\n";
    cout << "quit - завершить работу программы\n";
}