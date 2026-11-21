package com.example.raportmandiri.ui.components

private val SheetOverlap = 28.dp

/** Header hijau papan tulis dengan garis kuning kecil sebagai satu-satunya aksen. */
@Composable
fun AuthHeader(title: String, subtitle: String, modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(start = 24.dp, end = 24.dp, top = 40.dp, bottom = 24.dp + SheetOverlap)
    ) {
        Box(
            Modifier
                .width(40.dp)
                .height(4.dp)
                .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(2.dp))
        )
        Spacer(Modifier.height(16.dp))
        Text(title, style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.onPrimary)
        Spacer(Modifier.height(8.dp))
        Text(
            subtitle,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f)
        )
    }
}

/** Lembar form putih yang sedikit menimpa header. */
@Composable
fun FormSheet(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .offset(y = -SheetOverlap),
        shape = RoundedCornerShape(topStart = SheetOverlap, topEnd = SheetOverlap),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            Modifier
                .navigationBarsPadding()
                .padding(start = 24.dp, end = 24.dp, top = 28.dp, bottom = 16.dp)
        ) { content() }
    }
}

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    error: String? = null,
    hint: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onDone: (() -> Unit)? = null,
    enabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        enabled = enabled,
        isError = error != null,
        supportingText = (error ?: hint)?.let { msg -> { Text(msg) } },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = KeyboardActions(onDone = { onDone?.invoke() }),
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        shape = RoundedCornerShape(12.dp)
    )
}