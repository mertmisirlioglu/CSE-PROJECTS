import random

def find_distance_between_two_cities(distances, start, end):
    start_p = distances.loc[distances['İL ADI'] == start]
    distance = start_p[end].values[0]
    return distance


def find_total_distance_for_path(distances, path):
    total_distance = 0
    for x in range(len(path) - 1):
        total_distance += find_distance_between_two_cities(distances, path[x], path[x + 1])
    return total_distance


def select_two_random_city(distances):
    city_start = random.randint(0, 79)
    city_end = random.randint(0, 79)

    while city_start == city_end:
        city_end = random.randint(0, 80)

    return distances['İL ADI'][city_start], distances['İL ADI'][city_end]

