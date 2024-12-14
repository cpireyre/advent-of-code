(require '[clojure.string :as s])
(require '[clojure.core.matrix :as m])

(defn pos [line]
  (->> line
       (re-seq #"-?\d+")
       (mapv parse-long)))

(defn displace [[sx sy] seconds [px py vx vy]]
  [(mod (+ px (* seconds vx)) sx)
   (mod (+ py (* seconds vy)) sy)])

(defn quadrant [[x y] [px py]]
  (let [cx (/ (dec x) 2)
        cy (/ (dec y) 2)]
    (cond
      (or (= px cx) (= py cy)) nil
      (and (> px cx) (< py cy)) :northeast
      (and (< px cx) (< py cy)) :northwest
      (and (< px cx) (> py cy)) :southwest
      (and (> px cx) (> py cy)) :southeast)))

(defn solve [file size]
  (->> file
       slurp
       s/split-lines
       (map pos)
       (map (partial displace size 100))
       (map (partial quadrant size))
       frequencies
       (#(dissoc % nil))
       vals
       (reduce *)))


(import '[java.awt.image BufferedImage]
        '[javax.imageio ImageIO]
        '[java.io File]
        '[java.awt Color])

(defn save-grid-as-image
  "Yeah thanks Claude for this one"
  [grid filename & {:keys [cell-size]
                                           :or {cell-size 10}}]
  (let [width (* (m/dimension-count grid 1) cell-size)
        height (* (m/dimension-count grid 0) cell-size)
        img (BufferedImage. width height BufferedImage/TYPE_INT_RGB)
        g (.getGraphics img)]
    ;; Fill background white
    (.setColor g Color/BLACK)
    (.fillRect g 0 0 width height)
    ;; Draw robots as black squares
    (.setColor g Color/WHITE)
    (doseq [y (range (m/dimension-count grid 0))
            x (range (m/dimension-count grid 1))
            :when (= \R (m/mget grid y x))]
      (.fillRect g (* x cell-size) (* y cell-size) cell-size cell-size))
    ;; Save to file
    (ImageIO/write img "png" (File. filename))))

(defn solve-2 [file size seconds]
  (let [robots (->> file
                    slurp
                    s/split-lines
                    (map pos)
                    (map (partial displace size seconds)))
        grid (-> (m/new-matrix (second size) (first size))
                 (m/fill \space)
                 (m/mutable))]
    (doseq [[rx ry] robots]
      (m/mset! grid ry rx \R))
    grid))

; (println (solve "example.txt" [11 7]))
; (println (time (solve "puzzle-input.txt" [101 103])))
(doseq [step (range 10000)]
  (let [grid (solve-2 "puzzle-input.txt" [101 103] step)]
    (save-grid-as-image grid (str "./output/" step ".png") 2)))
