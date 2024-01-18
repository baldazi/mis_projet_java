# Exemple de script GnuPlot
set terminal png
set output 'data/nombre_requetes.png'

# Titres et légendes
set title 'Nombre de requêtes présentes dans le système'
set xlabel 'Temps de simulation'
set ylabel 'Nombre de requêtes'

# Tracer la courbe
plot 'donnees.txt' using 1:2 with linespoints title 'Nombre de requêtes'
