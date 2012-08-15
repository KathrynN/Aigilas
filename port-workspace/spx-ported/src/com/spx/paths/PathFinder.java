package com.spx.paths;
    public class PathFinder
    {
        private static PriorityQueue<Path> queue = new PriorityQueue<Path>(100,new Comparator<Path>() {
        private static Point2 node = new Point2(0, 0);
        private static Path path;
        private static List<Point2> neighbors = new ArrayList<Point2>();

        public static Path FindNextMove(Point2 start,Point2 destination,boolean nextMoveOnly)
        {
            queue.clear();
            start.Reset(start.GridX,start.GridY);
            destination.Reset(destination.GridX, destination.GridY);
            queue.add(PathFactory.Create(start,destination));
            while (!queue.isEmpty())
            {
                path = queue.peek();
                if (path.IsDone())
                {
                    return path;
                }
                neighbors.clear();
                neighbors = path.GetNeighbors();
                if(neighbors.size()==0)
                {
                    continue;
                }
                for(Point2 neighbor:neighbors)
                {
                    neighbor.SetWeight(HitTest.GetDistanceSquare(neighbor, destination));
                }
                
                while(neighbors.size()>0)
                {
                    node = neighbors.get(RNG.Next(0, neighbors.size()));
                    for(Point2 neighbor:neighbors)
                    {
                        if (neighbor.Weight < node.Weight)
                        {
                            node = neighbor;
                        }
                    }
                    neighbors.remove(node);
                    if (!CoordVerifier.IsBlocked(node) || node.IsSameSpot(destination))
                    {
                        node.SetWeight(Point2.CalculateDistanceSquared(node, path.GetLastStep()));
                        Path newPath = PathFactory.Create(path);
                        if(newPath.Add(node))
                        {
                            queue.add(newPath);
                            break;    
                        }
                    }
                }
            }
            return null;
        }
    }