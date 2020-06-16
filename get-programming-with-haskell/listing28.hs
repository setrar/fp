import qualified Data.Map as Map

type LatLong = (Double,Double)

-- At the Equator
earthRadiusInMiles = 3961.0
earthRadiusInKilometers = 6356.0

locationDB :: Map.Map String LatLong
locationDB = Map.fromList [("Arkham",(42.6054,-70.7829))
                          ,("Innsmouth",(42.8250,-70.8150))
                          ,("Carcosa",(29.9714,-90.7694))
                          ,("New York",(40.7776,-73.9691))
                          ,("Antananarivo",(-18.8792, 47.5079))
                          ,("Toronto",(43.6510, -79.3470))]

toRadians :: Double -> Double
toRadians degrees = degrees * pi / 180

latLongToRads :: LatLong -> (Double,Double)
latLongToRads (lat,long) = (rlat,rlong)
 where rlat = toRadians lat
       rlong = toRadians long

-- ghci>  haversine (43.6510, -79.3470) (-18.8792, 47.5079)

haversine :: LatLong -> LatLong -> Double
haversine coords1 coords2 = earthRadius * c
 where (rlat1,rlong1) = latLongToRads coords1
       (rlat2,rlong2) = latLongToRads coords2
       dlat = rlat2 - rlat1
       dlong = rlong2 - rlong1
       a = (sin (dlat/2))^2 + cos rlat1 * cos rlat2 * (sin (dlong/2))^2
       c = 2 * atan2 (sqrt a) (sqrt (1-a))
       earthRadius = earthRadiusInKilometers

haversineMaybe :: Maybe LatLong -> Maybe LatLong -> Maybe Double
haversineMaybe Nothing _ = Nothing
haversineMaybe _ Nothing = Nothing
haversineMaybe (Just val1) (Just val2) = Just (haversine val1 val2)


-- Using Functor and Applicative

-- ghci> startingCity = Map.lookup "Toronto" locationDB 
-- ghci> destCity = Map.lookup "Antananarivo" locationDB 
-- ghci> haversine <$> startingCity <*> destCity 

-- ghci> startingCity = Map.lookup "Paris" locationDB 
-- ghci> haversine <$> startingCity <*> destCity 

