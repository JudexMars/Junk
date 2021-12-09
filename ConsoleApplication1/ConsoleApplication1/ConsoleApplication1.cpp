#include <iostream>

using namespace std;

void Func() {
    bool error = true;

    if (error) {
        throw string("What?");
    }
}

int main()
{
    try { Func(); }
    catch (int e) { cout << "Error code: " << e << endl; }
    catch (char const* e) { cout << "Error message: " << e << endl; }
    catch (string &e) { cout << "Error message: " << e << endl; }
}
