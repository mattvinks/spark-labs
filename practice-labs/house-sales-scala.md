<link rel='stylesheet' href='../assets/css/main.css'/>


# House Sales Analytics

## Step 1 : Inspect House Sales data

[sample house sales data](/data/house-prices/house-sales-sample.csv)

```
      ID    Date SalePrice PropertyID  PropertyType Bedrooms Bathrooms SqFtTotLiving SqFtLot YrBuilt ZipCode
 1     1 9/16/14    280000    1000102     Multiplex        6      3.00          2400    9373    1991   98002
 2     2 6/16/06   1000000    1200013 Single Family        4      3.75          3764   20156    2005   98166
 3     3 1/29/07    745000    1200019 Single Family        4      1.75          2060   26036    1947   98166
 4     4 2/25/08    425000    2800016 Single Family        5      3.75          3200    8618    1966   98168
 5     5 3/29/13    240000    2800024 Single Family        4      1.75          1720    8620    1948   98168
 6     6 3/30/09    349900    3600090     Townhouse        2      1.50           930    1012    2008   98144
 7     7 8/28/13    327500    3800004 Single Family        3      1.50          1750   34465    1961   98178
 8     8 5/24/07    347000    3800009 Single Family        4      1.75          1860   14659    1963   98178
 9     9 9/22/06    220400    6600055 Single Family        2      1.00           990    5324    1930   98032
10    10 8/22/06    437500    7200080     Multiplex        4      2.00          1980   10585    1924   98055
# ... with 27,053 more rows
```

## Step 2 : Read CSV
```scala
val house_sales = spark.read.
                  option("header", "true").
                  csv("/data/house-prices/house-sales-simplified.csv")

house_sales.printSchema
house_sales.show
```

Register as temptable for SQL queries  

```scala
house_sales.createOrReplaceTempView("house_sales")
```


## Step 3: Calculate sale numbers per bedrooms
sample output (not actual numbers)
```
    bedrooms    count
        1        100
        2        150
        3        200
```

Hint 1  : `groupby`  
Hint 2  : or use SQL :-)

## Step 4: Get a 'sense' of data
We use `describe`

```scala
house_sales.describe("SalePrice", "Bedrooms").show()
```

## Step 5 : Find prices per bedrooms
Find avg, min, max prices for each bedrooms

## Step 6 : Find the most expensive zip code
