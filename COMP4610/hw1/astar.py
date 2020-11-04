from utils import find_distance_between_two_cities


class Node:
    def __init__(self, name: (), parent: ()):
        self.name = name
        self.parent = parent
        self.g = 0  # Distance to start node
        self.h = 0  # Distance to goal node
        self.f = 0  # Total cost

    def __lt__(self, other):
        return self.f < other.f

    def __repr__(self):
        return (self.name)

    def __eq__(self, other):
        if type(other) is type(self):
            return self.name == other.name
        return False


def astar_search(graph, distances, start, end,is_print):
    open = []
    closed = []
    start_node = Node(start, None)
    goal_node = Node(end, None)
    open.append(start_node)
    nodes_visited = 0
    while len(open) > 0:
        open.sort()
        current_node = open.pop(0)
        nodes_visited += 1
        closed.append(current_node)
        if is_print:
            print(current_node)
        if current_node == goal_node:
            path = []
            while current_node != start_node:
                path.append(current_node.name)
                current_node = current_node.parent
            path.append(start_node.name)
            return path[::-1], nodes_visited

        for next in graph.get(current_node.name, []):
            neighbor = Node(next, current_node)

            if neighbor in closed:
                continue
            # Generate heuristics (Straight-Line distance)
            neighbor.g = find_distance_between_two_cities(distances, start_node.name, neighbor.name)
            neighbor.h = find_distance_between_two_cities(distances, goal_node.name, neighbor.name)
            neighbor.f = neighbor.g + neighbor.h

            if add_to_open(open, neighbor) == True:
                open.append(neighbor)
    return None


def add_to_open(open, neighbor):
    for node in open:
        if neighbor == node and neighbor.f >= node.f:
            return False
    return True
