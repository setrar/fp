data Point = FloatPoint Float Float
           | IntPoint Int Int

coord :: Point -> (Float, Float)
coord (FloatPoint x y) = (x,y)
coord (IntPoint x y) = (realToFrac x, realToFrac y)

main = do print (coord (FloatPoint 1 2))
          print (coord (IntPoint 1 2))
