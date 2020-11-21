import unittest
from Person import Person


class MyTestCase(unittest.TestCase):

    def test_birthday_happened(self):
        person1 = Person("Dick", 8, 30, 1987)
        person2 = Person("Jane", 8, 31, 1987)
        person3 = Person("Bob", 9, 1, 1987)

        self.assertTrue(person1.birthday_occurred())
        self.assertTrue(person2.birthday_occurred())
        self.assertFalse(person3.birthday_occurred())

    def test_getName(self):
        person1 = Person("Dick", 8, 29, 1987)
        person2 = Person("", 8, 29, 1987)

        self.assertEquals("Dick", person1.get_name())
        self.assertEqual("", person2.get_name())


if __name__ == '__main__':
    unittest.main()
