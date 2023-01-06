#include <iostream>

using namespace std;

class A {
public:
    virtual ~A(){
        cout << "A destructor" << endl;
    }
};
class B : public A {
public:
    ~B(){
        cout << "B destructor" << endl;
    }
};

int main(){
    B* pointer;
    pointer = dynamic_cast<B*>(new A());
    delete pointer;
    cout << "End";
}