# university-Intranet
An University Intranet, for a project in J2EE

# DB :


    --
    -- Database: `cours_j2ee`
    --
    
    -- --------------------------------------------------------
    
    --
    -- Table structure for table `administrateur`
    --
    
    CREATE TABLE `administrateur` (
      `id` int(11) NOT NULL,
      `nom` varchar(200) NOT NULL,
      `prenom` varchar(200) NOT NULL,
      `email` varchar(200) NOT NULL,
      `civilite` varchar(200) DEFAULT NULL,
      `adresse` varchar(1000) DEFAULT NULL,
      `telephone` varchar(200) DEFAULT NULL,
      `motdepasse` varchar(200) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
    -- --------------------------------------------------------
    
    --
    -- Table structure for table `enseignant`
    --
    
    CREATE TABLE `enseignant` (
      `id` int(11) NOT NULL,
      `nom` varchar(200) NOT NULL,
      `prenom` varchar(200) NOT NULL,
      `email` varchar(200) NOT NULL,
      `civilite` varchar(200) DEFAULT NULL,
      `adresse` varchar(1000) DEFAULT NULL,
      `telephone` varchar(200) DEFAULT NULL,
      `motdepasse` varchar(200) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
    -- --------------------------------------------------------
    
    --
    -- Table structure for table `etudiant`
    --
    
    CREATE TABLE `etudiant` (
      `id` int(11) NOT NULL,
      `nom` varchar(200) NOT NULL,
      `prenom` varchar(200) NOT NULL,
      `email` varchar(200) NOT NULL,
      `classe` varchar(200) NOT NULL,
      `civilite` varchar(200) DEFAULT NULL,
      `adresse` varchar(1000) DEFAULT NULL,
      `telephone` varchar(200) DEFAULT NULL,
      `motdepasse` varchar(200) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
    -- --------------------------------------------------------
    
    --
    -- Table structure for table `module`
    --
    
    CREATE TABLE `module` (
      `id` int(100) NOT NULL,
      `matiere` varchar(500) NOT NULL,
      `classe` varchar(500) NOT NULL,
      `description` varchar(1000) DEFAULT NULL,
      `nb_heures` int(255) NOT NULL,
      `enseignant` int(255) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
    -- --------------------------------------------------------
    
    --
    -- Table structure for table `new`
    --
    
    CREATE TABLE `new` (
      `id` int(100) NOT NULL,
      `titre` varchar(500) NOT NULL,
      `description` varchar(500) NOT NULL,
      `image` varchar(500) NOT NULL,
      `date_expiration` varchar(500) NOT NULL,
      `actif` int(1) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
    -- --------------------------------------------------------
    
    --
    -- Table structure for table `note`
    --
    
    CREATE TABLE `note` (
      `id` int(100) NOT NULL,
      `etudiant` int(100) NOT NULL,
      `module` int(100) NOT NULL,
      `note` int(100) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
    --
    -- Indexes for dumped tables
    --
    
    --
    -- Indexes for table `administrateur`
    --
    ALTER TABLE `administrateur`
      ADD PRIMARY KEY (`id`);
    
    --
    -- Indexes for table `enseignant`
    --
    ALTER TABLE `enseignant`
      ADD PRIMARY KEY (`id`);
    
    --
    -- Indexes for table `etudiant`
    --
    ALTER TABLE `etudiant`
      ADD PRIMARY KEY (`id`);
    
    --
    -- Indexes for table `module`
    --
    ALTER TABLE `module`
      ADD PRIMARY KEY (`id`);
    
    --
    -- Indexes for table `new`
    --
    ALTER TABLE `new`
      ADD PRIMARY KEY (`id`);
    
    --
    -- Indexes for table `note`
    --
    ALTER TABLE `note`
      ADD PRIMARY KEY (`id`);
    
    --
    -- AUTO_INCREMENT for dumped tables
    --
    
    --
    -- AUTO_INCREMENT for table `administrateur`
    --
    ALTER TABLE `administrateur`
      MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
    --
    -- AUTO_INCREMENT for table `enseignant`
    --
    ALTER TABLE `enseignant`
      MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
    --
    -- AUTO_INCREMENT for table `etudiant`
    --
    ALTER TABLE `etudiant`
      MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
    --
    -- AUTO_INCREMENT for table `module`
    --
    ALTER TABLE `module`
      MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
    --
    -- AUTO_INCREMENT for table `new`
    --
    ALTER TABLE `new`
      MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
    --
    -- AUTO_INCREMENT for table `note`
    --
    ALTER TABLE `note`
      MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
