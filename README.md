# dataset-io

This is a (minimal) set of dependencies needed to make tech.ml.dataset and tabelocth be able to use these 
file formats out of the box:

- Excel
- Arrow
- Parquet

# how to use

Add this to deps.edn (apart from tech.ml.dataset or tablecoth):

```
scicloj/datase-io {:git/url "https://github.com/scicloj/dataset-io"
                   :git/sha "xxxx"}

```

The next call register handlers, so that `->dataset` (TMD)  or `dataset` (tablecloth) can read the file types above.
```
((requiring-resolve 'scicloj.dataset-io/enable-all-input-formats!))
```

So you can do calls like:
```

(tablecloth.api/dataset "data/singleSheet.xlsx")
(tablecloth.api/dataset "/workspaces/tablecloth/data/alldtypes.arrow-feather" {:file-type:arrow})
(tablecloth/dataset "/workspaces/tablecloth/data/userdata1.parquet")
(tech.v3.libs.fastexcel/workbook->datasets "data/twoSheets.xlsx")

```