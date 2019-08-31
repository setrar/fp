{-# LANGUAGE DuplicateRecordFields #-}
  
data Resource = Human
                { serial :: Int
                , name   :: String
                }
data Company  = Company
                { name      :: String
                , employees :: [Resource]
                }
