data Tree a = Empty | Node a (Tree a) (Tree a) deriving (Show)

freeTree :: Tree Char  
freeTree =   
    Node 'P'  
        (Node 'O'  
            (Node 'L'  
                (Node 'N' Empty Empty)  
                (Node 'T' Empty Empty)  
            )  
            (Node 'Y'  
                (Node 'S' Empty Empty)  
                (Node 'A' Empty Empty)  
            )  
        )  
        (Node 'L'  
            (Node 'W'  
                (Node 'C' Empty Empty)  
                (Node 'R' Empty Empty)  
            )  
            (Node 'A'  
                (Node 'A' Empty Empty)  
                (Node 'C' Empty Empty)  
            )  
        )

-- ************* Taking a walk ********************

-- Not so good

changeToP' :: Tree Char -> Tree Char  
changeToP' (Node x l (Node y (Node _ m n) r)) = Node x l (Node y (Node 'P' m n) r)

-- Replace first node with Z to test my understanding of the above function

changeTop'' :: Tree Char -> Tree Char
changeTop'' (Node _ l r) = Node 'Z' l r

data Direction = L | R deriving (Show)  

type Directions = [Direction]  
  
changeToP :: Directions -> Tree Char -> Tree Char  
changeToP (L:ds) (Node x l r) = Node x (changeToP ds l) r  
changeToP (R:ds) (Node x l r) = Node x l (changeToP ds r)  
changeToP [] (Node _ l r) = Node 'P' l r  

elemAt :: Directions -> Tree a -> a  
elemAt (L:ds) (Node _ l _) = elemAt ds l  
elemAt (R:ds) (Node _ _ r) = elemAt ds r  
elemAt [] (Node x _ _) = x 

-- ghci> let newTree = changeToP [R,L] freeTree  
-- ghci> elemAt [R,L] newTree  
-- 'P'  

-- changing the character Node in the Tree

changeNode :: Directions -> Tree Char -> Char -> Tree Char  
changeNode (L:ds) (Node x l r) n = Node x (changeNode ds l n) r  
changeNode (R:ds) (Node x l r) n = Node x l (changeNode ds r n)  
changeNode [] (Node _ l r) n = Node n l r  

-- ghci> let newTree = changeNode [R,L] freeTree 'Z'
-- ghci> elemAt [R,L] newTree 
-- 'Z'

-- ********** A trail of breadcrumbs **************

type Breadcrumbs' = [Direction]

goLeft' :: (Tree a, Breadcrumbs') -> (Tree a, Breadcrumbs')  
goLeft' (Node _ l _, bs) = (l, L:bs)

goRight' :: (Tree a, Breadcrumbs') -> (Tree a, Breadcrumbs')  
goRight' (Node _ _ r, bs) = (r, R:bs)

-- walking along our tree clearer, we can use the -: function
-- reading from left to right instead of backwards

(-:) :: a -> (a -> b) -> b
x -: f = f x

-- ghci> (freeTree, []) -: goRight' -: goLeft'
-- (Node 'W' (Node 'C' Empty Empty) (Node 'R' Empty Empty),[L,R])

-- ********** Going back up **************

data Crumb a = LeftCrumb a (Tree a) | RightCrumb a (Tree a) deriving (Show)  

type Breadcrumbs a = [Crumb a]

goLeft :: (Tree a, Breadcrumbs a) -> (Tree a, Breadcrumbs a)  
goLeft (Node x l r, bs) = (l, LeftCrumb x r:bs)

goRight :: (Tree a, Breadcrumbs a) -> (Tree a, Breadcrumbs a)  
goRight (Node x l r, bs) = (r, RightCrumb x l:bs) 

goUp :: (Tree a, Breadcrumbs a) -> (Tree a, Breadcrumbs a)  
goUp (t, LeftCrumb x r:bs) = (Node x t r, bs)  
goUp (t, RightCrumb x l:bs) = (Node x l t, bs)  

type Zipper a = (Tree a, Breadcrumbs a)

modify :: (a -> a) -> Zipper a -> Zipper a  
modify f (Node x l r, bs) = (Node (f x) l r, bs)  
modify f (Empty, bs) = (Empty, bs)  


