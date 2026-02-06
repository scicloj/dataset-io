# dataset-io

This is a (minimal) set of dependencies needed to make tech.ml.dataset and tabelocth be able to use these 
file formats out of the box:

- Excel
- Arrow
- Parquet

# how to use

add this to deps.edn (aprt from tech.ml.dataset or tablecoth):

```
dataset-io/dataset-io 
```

Register handlers, so `->dataset` (TMD)  or `dataset` (tablecloth) can read the file types above.
```
(require '[scicloj.dataset-io])
```

```

(tablecloth.api/dataset "data/singleSheet.xlsx")
(tablecloth.api/dataset "/workspaces/tablecloth/data/alldtypes.arrow-feather" {:file-type:arrow})
(tablecloth/dataset "/workspaces/tablecloth/data/userdata1.parquet")

(tech.v3.libs.fastexcel/workbook->datasets "data/twoSheets.xlsx")


```