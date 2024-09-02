package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

class ObraMaestra(
    val imagenId: Int,
    val titulo: String,
    val autor: String,
    val fecha: String,
    val descripcion: String,
)

@Composable
fun ArtSpaceApp() {
    // Creación de las obras maestras
    val obra1 = ObraMaestra(
        imagenId = R.drawable.imagen1,
        titulo = stringResource(id = R.string.titulo_1),
        autor = stringResource(id = R.string.autor),
        fecha = stringResource(id = R.string.fecha_1),
        descripcion = stringResource(id = R.string.descripcion_1)
    )
    val obra2 = ObraMaestra(
        imagenId = R.drawable.imagen2,
        titulo = stringResource(id = R.string.titulo_2),
        autor = stringResource(id = R.string.autor),
        fecha = stringResource(id = R.string.fecha_2),
        descripcion = stringResource(id = R.string.descripcion_2)
    )
    val obra3 = ObraMaestra(
        imagenId = R.drawable.imagen3,
        titulo = stringResource(id = R.string.titulo_3),
        autor = stringResource(id = R.string.autor),
        fecha = stringResource(id = R.string.fecha_3),
        descripcion = stringResource(id = R.string.descripcion_3)
    )
    val obra4 = ObraMaestra(
        imagenId = R.drawable.imagen4,
        titulo = stringResource(id = R.string.titulo_4),
        autor = stringResource(id = R.string.autor),
        fecha = stringResource(id = R.string.fecha_4),
        descripcion = stringResource(id = R.string.descripcion_4)
    )
    val obra5 = ObraMaestra(
        imagenId = R.drawable.imagen5,
        titulo = stringResource(id = R.string.titulo_5),
        autor = stringResource(id = R.string.autor),
        fecha = stringResource(id = R.string.fecha_5),
        descripcion = stringResource(id = R.string.descripcion_5)
    )

    val obras = listOf(
        obra1, obra2, obra3, obra4, obra5
    )

    var indiceActual by rememberSaveable {
        mutableIntStateOf(0)
    }

    // Estructura final
    Box {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.5F
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
            DescriptionPicture(obra = obras[indiceActual])
            Spacer(modifier = Modifier.height(50.dp))
            ButtonsNextPreview(
                onPreviousClick = {
                    if (indiceActual > 0) {
                        indiceActual--
                    }
                },
                onNextClick = {
                    if (indiceActual < obras.size-1) {
                        indiceActual++
                    }
                }
            )
        }
    }


}

@Composable
fun DescriptionPicture(
    obra: ObraMaestra,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(obra.imagenId),
            contentDescription = obra.descripcion,
            modifier = modifier
                .size(350.dp)
                .border(8.dp, colorResource(id = R.color.celeste6), RoundedCornerShape(8.dp))
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = obra.titulo,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.celeste7)
        )
        Text(
            text = "${obra.autor} (${obra.fecha})",
            fontSize = 16.sp
        )
    }
}

@Composable
fun ButtonsNextPreview(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onPreviousClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.celeste6)
            )
        ) {
            Text(
                text = stringResource(id = R.string.bn_retroceder),
                fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.width(24.dp))
        Button(
            onClick = onNextClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.celeste6)
            )
        ) {
            Text(
                text = stringResource(id = R.string.bn_avanzar),
                fontSize = 24.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}