INSERT INTO public.tasks (id, task_name, date) VALUES
    (1, 'Shopping', '2020-03-11'),
    (2, 'House chores', '2020-03-08'),
    (3, 'Visit a doctor', '2020-02-10');

INSERT INTO public.elements (id, element_name, element_details, task) VALUES
    (1, 'Lidl', 'Carrot, Tomato, Pasta, Potato', 1),
    (2, 'Misiek', 'Ham, Cheese, Milk', 1),
    (3, 'Cleaning', 'Vacuum, Wash the floor, Dust', 2),
    (4, 'Tidiness', 'Iron clothes, Laundry', 2),
    (5, 'Gastroenterologist', '12.03.2020 at 14:00, dr. Wurst, st. Midwest', 3);
