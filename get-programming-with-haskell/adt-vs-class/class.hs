class Point a where
    coord :: a -> (Float, Float)

data FloatPoint = FloatPoint Float Float
instance Point FloatPoint where
    coord (FloatPoint x y) = (x,y)

data IntPoint = IntPoint Int Int
instance Point IntPoint where
    coord (IntPoint x y) = (realToFrac x, realToFrac y)

main = do print (coord (FloatPoint 1 2))
          print (coord (IntPoint 1 2))
