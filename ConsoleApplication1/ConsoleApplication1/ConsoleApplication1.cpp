#include <iostream>
#include <exception>

using namespace std;

class MyException : public exception {
public:
    virtual const char* what() const throw() {
        return "Something really bad happened!";
    }
};

class Memories {
public:
    Memories() {
        char* pMemory = new char[999];
        delete[] pMemory;
        cout << "Memory allocated and then freed of its burden" << endl;
    }
};

class Test {
public:
    void Halt() {
        throw MyException();
    }
};

void Func() {
    bool error = true;

    if (error) {
        throw string("What?");
    }
}

void goesWrong() {
    bool error1 = true;
    bool error2 = false;

    if (error1) { throw bad_alloc(); }
    if (error2) { throw exception(); }
}

int main()
{
    try { Func(); }
    catch (int e) { cout << "Error code: " << e << endl; }
    catch (char const* e) { cout << "Error message: " << e << endl; }
    catch (string &e) { cout << "Error message: " << e << endl; }

    try {
        Memories m;
    }
    catch (bad_alloc& e) { cout << "Memory CAN NOT be allocated!" << e.what() << endl; }

    
    Test t;
    try {
        t.Halt();
    }
    catch (MyException& e) { cout << e.what() << endl; }

    try { goesWrong(); }
    catch (bad_alloc& e) { cout << "Error: " << e.what() << endl; }
    catch (exception &e) { cout << "Error message: " << e.what() << endl; }
}
