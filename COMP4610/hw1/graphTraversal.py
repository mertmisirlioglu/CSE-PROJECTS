def bfs(graph, start, end, is_print):
    nodes_visited = 0
    q = []
    visited=list()
    q.append([start])
    while q:
        path = q.pop(0)
        nodes_visited += 1
        if is_print:
            print(path)
        node = path[-1]
        
        if node == end:
            return path, nodes_visited

        if node not in visited:
            visited.append(node)
            
            for adjacent in graph.get(node, []):
                new_path = list(path)
                new_path.append(adjacent)
                q.append(new_path)

        

        


def dfs(graph, start, end, is_print):
    nodes_visited = 0
    stack = [(start, [start])]
    visited = set()
    while stack:
        vertex, path = stack.pop()
        nodes_visited += 1
        if is_print:
            print(path)
        if vertex not in visited:
            if vertex == end:
                return path, nodes_visited
            visited.add(vertex)

            for adjacent in graph.get(vertex, []):
                stack.append((adjacent, path + [adjacent]))