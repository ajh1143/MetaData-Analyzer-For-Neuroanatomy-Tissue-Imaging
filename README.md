# Metadata_ImageJ_Neuroanatomy

Program for parsing a series of text files, returning the X/Y/Z stage positions recorded in metadata of images taken with fluorescent microscopy of genetically labelled(TD-Tomato) GABAergic interneurons in the limbic system. 

Returns a list of X/Y/Z coordinates for each tile of a large image series, where the tiles are combined to re-create a full 3d image. Upload to ImageJ as the preferred tiling methodology to reconstruct the images. Solved labaratory problem with native set-up where 'stitched' images had large gaps between tiles, by exploiting metadata of stage coordinates to map the images.
