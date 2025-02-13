package com.wordline.quiz.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wordline.quiz.card

@Composable
fun CVScreen(){
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "CV",
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(all = 10.dp)
        )
    }
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Text(
            "Emma Salot",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier.padding(top = 35.dp, start = 10.dp)
        )
        card(
            "Expérience professionnelle",
            "Septembre 2023/En cours - Davidson SI Nord - Alternante  \n \n" + "Avril-Juin 2023 - Davidson SI Nord - Stagiaire \n \n " + "Avril-Juin 2022 - Misyl Services - Stagiaire \n"
        )
        card(
            "Formations",
            "2020-2022 - D.U.T informatique - IUT A Villeneuve d’Ascq \n \n" + "2022-2023 - Licence 3 informatique - Université de Lille \n \n " + "2023-2024 - Bachelor 3 INFO - Efficom \n"
        )
        card(
            "Compétences",
            "Anglais : Cambridge English Certificate / Level B1 / 2018, TOEIC / Level C1 / Score : 950 / 2022 \n \n" + "Informatique : Python, HTML/CSS, Java, C, SQL, VueJS, Typescript \n \n "
        )
        card(
            "Centres d'intérêt",
            "Loisirs : Trompette, kalimba, films et séries en VO (anglais) " + "Sports : Danse, Natation \n \n "
        )
    }
}