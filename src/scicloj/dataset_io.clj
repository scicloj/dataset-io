(ns scicloj.dataset-io)

(defn enable-all-input-formats! []
  (require 'tech.v3.libs.fastexcel)
  (require 'tech.v3.libs.arrow)
  (require 'tech.v3.libs.parquet))

(comment 
  ((requiring-resolve 'scicloj.dataset-io/enable-all-input-formats!))
  )