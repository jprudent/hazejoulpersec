(defn product [coordinates [point weight] dim]
  (* ((point coordinates) dim) weight))

(defn barycentre

  ([problem coordinates]
   (map #(barycentre problem coordinates %) [0 1]))

  ([problem coordinates dim]
    (int (/
      (apply + (map #(product coordinates % dim) problem))
      (apply + (vals problem))))))

(defn barycentres [problems coordinates]
  (map #(barycentre % coordinates) problems))
